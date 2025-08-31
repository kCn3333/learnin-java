package kcn333.securityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/loginForm")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }



}
