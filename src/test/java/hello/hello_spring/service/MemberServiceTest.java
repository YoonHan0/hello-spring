package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;       // 이게 추가되어야 Assertions 를 생략할 수 있다.
import static org.junit.jupiter.api.Assertions.*;
// @SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.
// @Transactional : 테스트 케이스에 이 어노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백을 한다. 이러면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // 1. 이처럼 new MemoryMemberRepository()를 해서 새로운 인스턴스를 생성하면 MemberService에서 new Mem.. 한 인스턴스와 서로 다른 인스턴스를 바라보기 때문에 데이터가 맞지 않을 수 있음
    // 2. 현재는 static 으로 선언되어 있어서 상관은 없지만 본래는 동일한 인스턴스를 사용해야함

    // 3. 그래서 아래처럼 수정함 + MemberService.java 도 같이 + beforeEach() 메서드 추가
    @Autowired MemberService memberService;            // 일반적으로 테스트 케이스에서는 DI를 필드 주입으로 한다. 왜?) 테스트 케이스에서 의존성 주입한 필드를 다른 곳에서 사용할 일이 없기 때문에
    @Autowired MemberRepository memberRepository;      // 이전 소스 : MemoryMemberRepository memberRepository;

    /*
    * - 테스트 코드는 메서드명을 한글로 작성해도 됨. 영어권 사람들과 같이 일을 하는게 아니라면
    * - 테스트 코드는 정상 동작도 중요하지만, 예외를 확인하는게 더 중요하다.
    *
    * > 단축키
    * ctrl + r | 이전에 실행한 동작 다시 실행
    * */

    /* @Transactional 어노테이션으로 대체 가능 */
//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }
//
//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearStore();
//    }

    @Test
    void 회원가입() {
        // given, 어떤 데이터가
        Member member = new Member();
        member.setName("spring");

        // when,  이 시점에 주어졌을 때
        Long saveId = memberService.join(member);

        // then, 이런 결과가 나온다
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
//      assertThat(member.getName()).isEqualTo(findMember.getName());   // import static이 되어야 Assertions 를 생략할 수 있다.
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
//        방법[1]
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

//        방법[2]
//        memberService.join(member1);
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));       // assertThrows(발생하는_예외_클래스, 실행하는_콜백_함수);
                                                                                            // "콜백 함수를 실행했을 때 특정 예외가 발생한다"라고 설정하는 로직임
//        방법[3]
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}