package me.hjhng125.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import me.hjhng125.junit.member.Member;
import me.hjhng125.junit.member.MemberRepository;

public class MemberRepositoryJunit4Test {

  private MemberRepository repository;

  @Before
  public void before() {
    repository = new MemberFakeRepository();
  }

  @Test
  public void 레포지토리의_모든_멤버를_조회한다() {
    List<Member> all = repository.findAll();

    all.forEach(member -> assertThat(member).isNotNull());
  }
}