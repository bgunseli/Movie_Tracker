package com.obss.movietracker.service;

import com.obss.movietracker.model.movie.Movie;
import com.obss.movietracker.model.movie.UserMovieList;
import com.obss.movietracker.model.user.User;
import com.obss.movietracker.repository.MovieRepository;
import com.obss.movietracker.repository.UserMovieListRepository;
import com.obss.movietracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private UserMovieListRepository userMovieListRepository;
    private JwtTokenService jwtTokenService;

    @Autowired
    public UserService(MovieRepository movieRepository, UserRepository userRepository, UserMovieListRepository userMovieListRepository, JwtTokenService jwtTokenService) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.userMovieListRepository = userMovieListRepository;
        this.jwtTokenService = jwtTokenService;
    }

    public List<Movie> getList(String listType, HttpServletRequest request) {
        Long userId = jwtTokenService.getCurrentUserId(request);
        Optional<User> optionalUser = userRepository.findById(userId);
        Movie emptyMovie = movieRepository.findByName("");
        List<UserMovieList> userMovieLists = optionalUser.map(user -> userMovieListRepository
                .findAllByUserAndListTypeAndMovieIsNot(user, listType, emptyMovie)).orElse(null);
        List<Movie> movies = new ArrayList<>();
        Objects.requireNonNull(userMovieLists).forEach(movieList -> movies.add(movieList.getMovie()));
        return movies;
    }

    public List<String> getLists(HttpServletRequest request) {
        Long userId = jwtTokenService.getCurrentUserId(request);
        Optional<User> optionalUser = userRepository.findById(userId);
        Movie emptyMovie = movieRepository.findByName("");
        List<UserMovieList> userMovieLists = optionalUser.map(user -> userMovieListRepository
                .findAllByUserAndMovie(user, emptyMovie)).orElse(null);
        List<String> lists = new ArrayList<>();
        Objects.requireNonNull(userMovieLists).forEach(movieList -> lists.add(movieList.getListType()));
        return lists;
    }

    public UserMovieList addToList(Long movieId, String listType, HttpServletRequest request) {
        Long userId = jwtTokenService.getCurrentUserId(request);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalMovie.isPresent() && optionalUser.isPresent()) {
            if (userMovieListRepository.existsByUserAndMovieAndListType(optionalUser.get(), optionalMovie.get(), listType)) {
                return null;
            }
            UserMovieList userMovieList = new UserMovieList(optionalMovie.get(), optionalUser.get(), listType);
            userMovieListRepository.save(userMovieList);
            return userMovieList;
        }
        return null;
    }

    public boolean addNewList(String listType, HttpServletRequest request) {
        Long userId = jwtTokenService.getCurrentUserId(request);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent() && userMovieListRepository.existsByUserAndListType(optionalUser.get(), listType)) {
            return false;
        }
        UserMovieList userMovieList = new UserMovieList();
        Movie movie = movieRepository.findByName("");
        if (movie == null) {
            movie = new Movie();
            movie.setName("");
            movieRepository.save(movie);
        }
        userMovieList.setMovie(movie);
        userMovieList.setUser(optionalUser.get());
        userMovieList.setListType(listType);
        userMovieListRepository.save(userMovieList);
        return true;
    }
}
