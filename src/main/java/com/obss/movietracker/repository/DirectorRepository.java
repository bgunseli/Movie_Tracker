package com.obss.movietracker.repository;

import com.obss.movietracker.model.movie.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    boolean existsByName(String name);

    Director findByName(String name);
}
