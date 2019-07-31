package com.obss.movietracker.repository;

import com.obss.movietracker.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    Page<User> findAll(Pageable pageable);
}
