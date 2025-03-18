package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      // @Configuration 을 달면 Spring이 실행할 때 해당 클래스를 읽게 됨.
public class SpringConfig {

    @Bean           // Spring이 실행할 때 어노테이션을 보고 bean으로 등록하고 아래 로직을 실행함.
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    /* ----- ----- ----- -----
        Spring이 실행되면 MemberService, MemberRepository를 bean으로 등록하고, 위에 작성된 것처럼 MemberService에 memberRepository를 주입해준다.
    */
}
