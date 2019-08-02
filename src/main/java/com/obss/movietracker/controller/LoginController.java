package com.obss.movietracker.controller;

import com.obss.movietracker.model.user.Role;
import com.obss.movietracker.model.user.UserLoginBody;
import com.obss.movietracker.security.LoggedInUser;
import com.obss.movietracker.service.AdminService;
import com.obss.movietracker.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;
    private AdminService adminService;

    public LoginController(LoginService loginService, AdminService adminService) {
        this.loginService = loginService;
        this.adminService = adminService;
    }

    @GetMapping
    public void login() {

    }

    @PostMapping
    public String login(@RequestBody UserLoginBody userLoginBody, HttpServletResponse response) {
        String jwtToken = loginService.login(userLoginBody.getUsername(), userLoginBody.getPassword());
        Cookie cookie = new Cookie("Authorization", jwtToken);
        cookie.setMaxAge(604800);
        response.addCookie(cookie);
        LoggedInUser.setJwtToken(jwtToken);
        String jwt = LoggedInUser.getJwtToken();
        if (adminService.getUser(userLoginBody.getUsername()).getRole().equals(Role.ROLE_ADMIN)) {
            return "/movies";
        }
        else {
            return "/lists";
        }
    }
}
