package me.hjhng125.junit.member;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MemberRepository {

  List<Member> findAll();

  Page<Member> findAll(Pageable pageable);

  Member save(Member member);
}
