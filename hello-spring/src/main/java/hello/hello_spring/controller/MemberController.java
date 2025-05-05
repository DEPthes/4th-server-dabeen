package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired //연결쓸때 사용됨, 의존관계주입
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //전달할때 사용
    public String create(MemberForm) {
        Member member = new Member();
        member.setNAme(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    pub;ic String list(Model model){
        List<Member> members = membersService.findMembers();
        model.addAttributs(attributeName: "members", members);
        return "members/memberList";
    }


}
