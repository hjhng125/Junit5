package me.hjhng125.member;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.hjhng125.junit.member.MemberService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MemberService.class)
class MemberServiceJunit5Test {

  @Autowired
  private MemberService memberService;

}
