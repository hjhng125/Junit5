package me.hjhng125.member;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import me.hjhng125.junit.member.MemberService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MemberService.class)
public class MemberServiceJunit4Test {

  @Autowired
  private MemberService memberService;
}
