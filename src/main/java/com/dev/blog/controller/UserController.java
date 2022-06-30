package com.dev.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String join() {
        return "/user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String login() {
        return "/user/loginForm";
    }
}
