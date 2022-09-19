package me.hjhng125.member;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MemberRepository {

  List<Member> findAll();

  Member save(Member member);
}
