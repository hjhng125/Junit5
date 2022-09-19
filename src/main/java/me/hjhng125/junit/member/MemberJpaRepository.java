package me.hjhng125.junit.member;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface MemberJpaRepository extends JpaRepository<Member, Long>, MemberRepository {

}
