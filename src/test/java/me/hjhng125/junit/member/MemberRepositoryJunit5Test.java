package me.hjhng125.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.hjhng125.junit.member.Member;
import me.hjhng125.junit.member.MemberRepository;

class MemberRepositoryJunit5Test {

  private MemberRepository repository;

  @BeforeEach
  void beforeEach() {
    repository = new MemberFakeRepository();
  }

  @Test
  @DisplayName("레포지토리의 모든 멤버를 조회한다.")
  void find_all_test() {
    List<Member> all = repository.findAll();

    all.forEach(member -> assertThat(member).isNotNull());
  }
}