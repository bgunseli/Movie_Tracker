package com.obss.movietracker.service;

import com.obss.movietracker.model.movie.Director;
import com.obss.movietracker.model.movie.Movie;
import com.obss.movietracker.repository.DirectorRepository;
import com.obss.movietracker.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchService {

    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;

    @Autowired
    public SearchService(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    public List<Movie> searchMovie(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return movieRepository.findAllByNameIsNot(pageable, "");
    }

    public List<Movie> searchMovieByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAllByNameLikeAndNameIsNot(pageable, name, "");
    }

    public List<Movie> searchMovieByDirector(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Director director = directorRepository.findByName(name);
        if (director == null) {
            return Collections.emptyList();
        }
        return movieRepository.findAllByDirectorAndNameIsNot(pageable, directorRepository.findById(director.getId()).get(), "");
    }
}
