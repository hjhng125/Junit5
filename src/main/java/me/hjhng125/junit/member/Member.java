package me.hjhng125.junit.member;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.ToString;

@Entity
@ToString(exclude = "memberId")
public class Member {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long memberId;

  private String email;

  private String password;

  protected Member() {
  }

  @Builder
  public Member(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public static Member of(MemberCreateRequest request) {
    return Member.builder()
        .email(request.getEmail())
        .password(request.getPassword())
        .build();
  }

  public String email() {
    return email;
  }

  public String passowrd() {
    return password;
  }
}
