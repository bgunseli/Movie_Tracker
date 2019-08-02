package com.obss.movietracker.controller;

import com.obss.movietracker.model.movie.Movie;
import com.obss.movietracker.service.AdminService;
import com.obss.movietracker.service.SearchService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/movies")
public class MovieController {

    private AdminService adminService;
    private SearchService searchService;

    @Autowired
    public MovieController(AdminService adminService, SearchService searchService) {
        this.adminService = adminService;
        this.searchService = searchService;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return adminService.getMovies();
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam int page, @RequestParam int size,
                                    @RequestParam(required = false) String name, @RequestParam(required = false) String directorName) {
        if (name == null && directorName == null) {
            return searchService.searchMovie(page, size);
        }
        else if (name == null) {
            return searchService.searchMovieByDirector(page, size, directorName);
        }
        else if (directorName == null) {
            return searchService.searchMovieByName(page, size, name);
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") Long id) {
        return adminService.getMovie(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        adminService.addMovie(movie);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void addMovieWithId(@PathVariable("id") String id) {
        adminService.addMovieWithOmdb(id);
    }

    @PutMapping("/{id}")
    public void updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        adminService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        adminService.deleteMovie(id);
    }
}
