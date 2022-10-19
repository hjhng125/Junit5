package me.hjhng125.junit.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

  private final String email;
  
  private final String password;

  public String email() {
    return email;
  }

  public String password() {
    return password;
  }

  @Builder
  public MemberResponse(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public static MemberResponse from(Member member) {
    return MemberResponse.builder()
        .email(member.email())
        .password(member.passowrd())
        .build();
  }
}
