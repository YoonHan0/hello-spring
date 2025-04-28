package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 메서드명 규칙으로 처리를 해준다.
    // SELECT m FROM Member m WHERE m.name = ?
    // find, by name
    @Override
    Optional<Member> findByName(String name);

    // SELECT m FROM Member m WHERE m.name = ? AND m.id = ?
//    @Override
//    Optional<Member> findByNameAndId(String name, Long id);

}
