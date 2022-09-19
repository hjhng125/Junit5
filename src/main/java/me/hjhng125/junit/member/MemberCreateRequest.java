package me.hjhng125.junit.member;

import lombok.Builder;

public class MemberCreateRequest {

  private final String email;

  private final String password;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  @Builder
  public MemberCreateRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
