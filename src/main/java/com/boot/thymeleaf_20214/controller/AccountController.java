package com.boot.thymeleaf_20214.controller;


// 보안 컨트롤러

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String login(){
        log.info("#@#@ login()");

        return "account/login";
    }


}
