package com.spring.springbootstarter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
