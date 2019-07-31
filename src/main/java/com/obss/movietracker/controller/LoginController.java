package com.obss.movietracker.controller;

import com.obss.movietracker.model.user.UserLoginBody;
import com.obss.movietracker.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public void login(@RequestBody UserLoginBody userLoginBody, HttpServletResponse response) {
        String jwtToken =  loginService.login(userLoginBody.getUsername(), userLoginBody.getPassword());
        Cookie cookie = new Cookie("Authorization", jwtToken);
        cookie.setMaxAge(604800);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
