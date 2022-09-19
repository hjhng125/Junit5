package me.hjhng125.junit.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MemberService.class)
class MemberServiceJunit5Test {

  @Autowired
  private MemberService memberService;

  @MockBean
  private MemberRepository memberRepository;

  @Test
  @DisplayName("회원 전체 조회")
  public void find_all_test() {
    String email = "jinhyung.hong@togle.shop";
    PageRequest pageable = PageRequest.of(0, 10);
    List<Member> members = Collections.singletonList(Member.of(MemberCreateRequest.builder()
        .email(email)
        .password("1234")
        .build()));

    given(memberRepository.findAll(pageable))
        .willReturn(PageableExecutionUtils.getPage(members, pageable, members::size));

    Page<MemberResponse> memberPage = memberService.findAll(pageable);

    assertThat(memberPage.getTotalElements()).isEqualTo(1L);
    assertThat(memberPage.getTotalPages()).isEqualTo(1);
    assertThat(memberPage.getContent().size()).isEqualTo(1);
    memberPage.forEach(member -> assertThat(member.email()).isEqualTo(email));
  }

  @Test
  @DisplayName("회원 등록 요청 파라미터를 통해 저장")
  void save_test() {
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
