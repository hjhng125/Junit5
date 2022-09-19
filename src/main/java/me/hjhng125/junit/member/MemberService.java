package me.hjhng125.junit.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Page<MemberResponse> findAll(Pageable pageable) {
    return memberRepository.findAll(pageable)
        .map(MemberResponse::from);
  }

  public void save(MemberCreateRequest request) {
    Member member = Member.of(request);
    Member save = memberRepository.save(member);

    log.info("saved member.email = {}", save.email());
  }
}
