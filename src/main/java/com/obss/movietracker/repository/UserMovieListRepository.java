package com.obss.movietracker.repository;

import com.obss.movietracker.model.movie.Movie;
import com.obss.movietracker.model.movie.UserMovieList;
import com.obss.movietracker.model.movie.UserMovieListId;
import com.obss.movietracker.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMovieListRepository extends JpaRepository<UserMovieList, UserMovieListId> {

    List<UserMovieList> findAllByMovie(Movie movie);

    List<UserMovieList> findAllByUserAndMovie(User user, Movie movie);

    List<UserMovieList> findAllByUserAndListType(User user, String listType);

    List<UserMovieList> findAllByUserAndListTypeAndMovieIsNot(User user, String listType, Movie movie);

    boolean existsByUserAndMovieAndListType(User user, Movie movie, String listType);

    boolean existsByUserAndListType(User user, String listType);
}
