package me.hjhng125.junit.member;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MemberController.class)
@RunWith(SpringRunner.class)
public class MemberControllerJunit4Test {

  @MockBean
  private MemberService memberService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void 조회_테스트() throws Exception {
    String email = "jinhyung.hong@togle.shop";
    String password = "1234";

    PageRequest pageable = PageRequest.of(0, 10);
    List<MemberResponse> members = Collections.singletonList(MemberResponse.from(Member.builder()
        .email(email)
        .password(password)
        .build()));

    given(memberService.findAll(pageable))
        .willReturn(PageableExecutionUtils.getPage(members, pageable, members::size));

    mockMvc.perform(get("/members"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("content[0].email").value(email))
        .andExpect(jsonPath("content[0].password").value(password))
        .andExpect(jsonPath("pageable.offset").value(0))
        .andExpect(jsonPath("pageable.pageNumber").value(0))
        .andExpect(jsonPath("pageable.pageSize").value(10))
        .andReturn();
  }
}
