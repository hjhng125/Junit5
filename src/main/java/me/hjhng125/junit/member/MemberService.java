package me.hjhng125.member;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public void save(MemberCreateRequest request) {
    Member member = Member.of(request);
  }
}
