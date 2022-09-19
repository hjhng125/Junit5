package me.hjhng125.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import me.hjhng125.junit.member.Member;
import me.hjhng125.junit.member.MemberJpaRepository;

@DataJpaTest
class MemberJpaRepositoryJunit5Test {

  @Autowired
  private MemberJpaRepository repository;

  @Test
  @DisplayName("레포지토리의 모든 멤버를 조회한다.")
  void find_all_test() {
    List<Member> all = repository.findAll();

    all.forEach(member -> assertThat(member).isNotNull());
  }
}