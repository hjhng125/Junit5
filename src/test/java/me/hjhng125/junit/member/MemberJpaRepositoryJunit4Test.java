package me.hjhng125.junit.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import me.hjhng125.junit.member.Member;
import me.hjhng125.junit.member.MemberRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberJpaRepositoryJunit4Test {

  @Autowired
  private MemberRepository repository;

  @Test
  public void 레포지토리의_모든_멤버를_조회한다() {
    List<Member> all = repository.findAll();

    all.forEach(member -> assertThat(member).isNotNull());
  }

  @Test
  public void 회원을_레포지토리에_저장한다() {
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