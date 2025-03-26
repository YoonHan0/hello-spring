package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    /*
    * Spring이 실행될 때 Spring Container가 생성되는데 @Controller 어노테이션이 붙은 클래스를 빈으로 만들어서 관리한다.
    * Spring Container 에서 관리하면 new 로 인스턴스를 생성하는 것이 아니라 관리되고 있는 빈을 가져와서 사용해야 한다
    * 왜? new 해서 생성하게 되면 MemberController 가 아닌 다른 controller 에서도 service 를 사용할 수 있기 때문이다.
    * */

    private final MemberService memberService;

    @Autowired  // 의존성 주입 : DI, 연결하는 로직
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);     // key: members, value: members(변수)

        return "members/memberList";
    }
}
