package com.dev.blog.controller;

import com.dev.blog.config.auth.PrincipalDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class HomeController {

    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        System.out.println(principalDetail.getUsername());
        return "index";
    }
}
