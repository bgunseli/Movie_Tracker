package com.obss.movietracker.controller;

import com.obss.movietracker.model.movie.Movie;
import com.obss.movietracker.model.movie.UserMovieList;
import com.obss.movietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListController {

    private UserService userService;

    @Autowired
    public ListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{list-name}")
    public List<Movie> getMovieList(@PathVariable("list-name") String listName, HttpServletRequest request) {
        return userService.getList(listName, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{list-name}/{movie-id}")
    public UserMovieList addMovieToList(@PathVariable("list-name") String listName, @PathVariable("movie-id") Long movieId, HttpServletRequest request) {
        return userService.addToList(movieId, listName, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{list-name}")
    public boolean addNewList(@PathVariable("list-name") String listName, HttpServletRequest request) {
        return userService.addNewList(listName, request);
    }
}
