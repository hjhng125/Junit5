package me.hjhng125.junit.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MemberService.class)
public class MemberServiceJunit4Test {

  @Autowired
  private MemberService memberService;

  @MockBean
  private MemberRepository memberRepository;

  @Test
  public void 전체_조회() {
    String email = "jinhyung.hong@togle.shop";
    PageRequest pageable = PageRequest.of(0, 10);
    List<Member> members = Collections.singletonList(Member.of(MemberCreateRequest.builder()
        .email(email)
        .password("1234")
        .build()));

    given(memberRepository.findAll(pageable))
        .willReturn(PageableExecutionUtils.getPage(members, pageable, members::size));

    when(memberRepository.findAll(pageable)).thenReturn(PageableExecutionUtils.getPage(members, pageable, members::size));

    Page<MemberResponse> memberPage = memberService.findAll(pageable);

    assertThat(memberPage.getTotalElements()).isEqualTo(1L);
    assertThat(memberPage.getTotalPages()).isEqualTo(1);
    assertThat(memberPage.getContent().size()).isEqualTo(1);
    memberPage.forEach(member -> assertThat(member.email()).isEqualTo(email));
  }

  @Test
  public void 회원_등록_요청_파라미터를_통해_저장() {
    MemberCreateRequest me = MemberCreateRequest.builder()
        .email("jinhyung.hong@togle.shop")
        .password("1234")
        .build();

    Member saved = Member.of(me);

    given(memberRepository.save(any(Member.class))).willReturn(saved);
    memberService.save(me);

    verify(memberRepository).save(any(Member.class));
  }
}
