package com.spring.springbootstarter.controller;

import com.spring.springbootstarter.domain.Member;
import com.spring.springbootstarter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //컨트롤러 어노테이션으로 스프링 컨테이너에 빈으로 등록되어 관리된다.
public class MemberController {

    // 1번 방법 필드 선언후에 객체를 생성해서 할당 (안좋은 방법)
    //private final MemberService memberService = new MemberService();
    // 2번 DI 필드만 생성후에 생성자에 스프링컨테이너에 등록된 빈을 주입해줌.
    private final MemberService memberService;
    //컨트롤러가 객체로 생성될때 @Autowird 어노테이션이 있으면 스프링컨테이너에 빈으로 등록된것을 주입해줌 (Dependency Injection)
    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
    }

    //컨트롤러가 뷰화면의 주소를 리턴하면 리턴값의 위치에서 파일을 찾아 반환함
    @GetMapping("/member/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //submit시 method에 따라서 타고갈 컨트롤러를 가져감 Post, GetMapping에 연결된
    //주소가 같은데 화면쪽에서 메소드 타입을 보고 컨트롤러를 알아서 골라탐
    @PostMapping("/member/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model){

        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
