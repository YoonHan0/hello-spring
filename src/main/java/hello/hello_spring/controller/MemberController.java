package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    /*
    * @Controller 어노테이션이 붙어 있으면 Spring이 실행될 때 Spring Container가 생성되는데 어노테이션이 붙은 클래스를 빈으로 만들어서 관리한다.
    * Spring Container 에서 관리하면 new 로 인스턴스를 생성하는 것이 아니라 관리되고 있는 빈을 가져와서 사용해야 한다
    * 왜? new 해서 생성하게 되면 MemberController 가 아닌 다른 controller 에서도 service 를 사용할 수 있기 때문이다.
    * */

    private final MemberService memberService;

    @Autowired  // 의존성 주입 : DI, 연결하는 로직
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
