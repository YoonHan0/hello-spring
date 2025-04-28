package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    /*
    * JPA 는 EntityManager 로 모두 동작을 함
    * gradle 파일에 jpa dependency 를 추가하게 되면 Spring 이 현재 DB와 연결해서 EntityManager 를 생성해줌
    * 우리는 생성된 EntityManager 를 injection 받기만 하면 된다~!
    * */

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();       // 테이블이 아닌 Entity 객체를 대상으로 사용
        return result;
    }
}
