package com.obss.movietracker.controller;

import com.obss.movietracker.model.movie.Director;
import com.obss.movietracker.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    private AdminService adminService;

    @Autowired
    public DirectorController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addDirector(@RequestBody Director director) {
        adminService.addDirector(director);
    }

    @PutMapping("/{id}")
    public void updateDirector(@PathVariable("id") Long id, @RequestBody Director director) {
        adminService.updateDirector(id, director);
    }

    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable("id") Long id) {
        adminService.deleteDirector(id);
    }
}
