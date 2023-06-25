package com.spring.springbootstarter.controller;

import com.spring.springbootstarter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    }
}
