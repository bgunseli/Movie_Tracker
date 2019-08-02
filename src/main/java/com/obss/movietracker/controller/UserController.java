package com.obss.movietracker.controller;

import com.obss.movietracker.model.user.User;
import com.obss.movietracker.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private AdminService adminService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(AdminService adminService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return adminService.getUser(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        adminService.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        adminService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        adminService.deleteUser(id);
    }
}
