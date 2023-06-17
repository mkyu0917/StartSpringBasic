package com.spring.springbootstarter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){

        model.addAttribute("data","아리!!");

        return "hello";
    }

    @GetMapping("/start-mvc")
    public String startMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "start-mvc";
    }

    @GetMapping("/spring-string")
    @ResponseBody //데이터를 문자열로 전달 가능(실제로 이렇게 쓰지이 않음)
    public String springString(@RequestParam("name") String name) {
        return "start-mvc" + name;
    }

    @GetMapping("/spring-api")
    @ResponseBody // 객체를 ResponseBody로 보내면 제이슨 형식으로 모냄
    public Hello springApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
