package me.hjhng125.junit.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberJpaRepositoryJunit5Test {

  @Autowired
  private MemberRepository repository;

  @Test
  @DisplayName("레포지토리의 모든 멤버를 조회한다.")
  void find_all_test() {
    List<Member> all = repository.findAll();

    all.forEach(member -> assertThat(member).isNotNull());
  }

  @Test
  @DisplayName("회원을 레포지토리에 저장한다.")
  void save_test() {
    String email = "jinhyung.hong@togle.shop";
    String password = "1234";
    Member me = Member.builder()
        .email(email)
        .password(password)
        .build();

    Member saved = repository.save(me);
    assertThat(saved).isNotNull();
    assertThat(saved.email()).isEqualTo(email);
    assertThat(saved.passowrd()).isEqualTo(password);
  }
}