package hello.hello_spring;

import hello.hello_spring.repository.JdbcTemplateMemberRepository;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.swing.*;

@Configuration      // @Configuration 을 달면 Spring이 실행할 때 해당 클래스를 읽게 됨.
public class SpringConfig {

/* --- JDBC Template 개발용 --- */
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    /* --- JPA 개발용 --- */
//    private EntityManager em;

//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {          // Spring이 실행할 때 어노테이션을 보고 bean으로 등록하고 아래 로직을 실행함.
        return new MemberService(memberRepository);
    }

    /* --- JPA 개발까지 사용 --- */
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
    /* ----- ----- ----- -----
        Spring이 실행되면 MemberService, MemberRepository를 bean으로 등록하고, 위에 작성된 것처럼 MemberService에 memberRepository를 주입해준다.
    */
}
