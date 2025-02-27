package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /* 테스트는 서로 의존관계가 설계되어야 함 */
    /* @AfterEach 를 달아놓으면 하나의 @Test가 끝날 때마다 실행되는 거 같음 */
    /* 테스트케이스를 만들고 -> 구현체 개발하는 방식을 => 테스트 주도 개발(TDD)라고 함 */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {

        Member member = new Member();
        member.setName("Spring");

        repository.save(member);        // 이게 DB에 데이터를 저장하는 동작처럼 구현한것
//      Optional.get()을 호출하기 전에 값이 존재하는지 확인하지 않으면서 warnning이 발생, isPresent()로 체크 후 get()을 호출하거나 아래 방법을 사용
//      Member result = repository.findById(member.getId()).get();

        Member result = repository.findById(member.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 회원을 찾을 수 없습니다: " + member.getId()));

        // Assertions.assertEquals(member, result);             // import org.junit.jupiter.api.Assertions;
        Assertions.assertThat(member).isEqualTo(result);        // import org.assertj.core.api.Assertions;  / static import를 하면 앞에 Assertions를 붙이지 않아도 사용 가능
    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("Spring1");

        repository.save(member1);       // 이게 DB에 데이터를 저장하는 동작처럼 구현한것

        Member member2 = new Member();
        member2.setName("Spring2");

        repository.save(member2);

        Optional<Member> optionalMember = repository.findByName("Spring1");
        if(optionalMember.isPresent()) {
            Member result = optionalMember.get();

            Assertions.assertThat(member1).isEqualTo(result);
        }
        else {
            throw new NoSuchElementException("해당 Name의 회원을 찾을 수 없습니다: " + member1.getName());
        }
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");

        repository.save(member1);       // 이게 DB에 데이터를 저장하는 동작처럼 구현한것

        Member member2 = new Member();
        member2.setName("Spring2");

        repository.save(member2);       // 이게 DB에 데이터를 저장하는 동작처럼 구현한것

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
