package com.obss.movietracker.repository;

import com.obss.movietracker.model.movie.Director;
import com.obss.movietracker.model.movie.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByNameIsNot(Pageable pageable, String name);

    List<Movie> findAllByNameAndNameIsNot(Pageable pageable, String name, String name1);

    List<Movie> findAllByDirectorAndNameIsNot(Pageable pageable, Director director, String name);

    Movie findByName(String name);

    boolean existsByName(String name);

}
