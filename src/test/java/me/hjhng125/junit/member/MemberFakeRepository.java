package me.hjhng125.member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.hjhng125.junit.member.Member;
import me.hjhng125.junit.member.MemberRepository;

public class MemberFakeRepository implements MemberRepository {

  private final ArrayList<Member> members = new ArrayList<>(Arrays.asList(
      new Member("hjhng125@nate.com", "1234"), new Member("hjhng125@gmail.com", "4321")
  ));

  @Override
  public List<Member> findAll() {
    return this.members;
  }

  @Override
  public Member save(Member member) {
    this.members.add(member);
    return member;
  }
}
