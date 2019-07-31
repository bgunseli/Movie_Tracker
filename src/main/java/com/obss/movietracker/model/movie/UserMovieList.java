package com.obss.movietracker.model.movie;

import com.obss.movietracker.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user_movie_list")
@IdClass(UserMovieListId.class)
public class UserMovieList implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn
    private User user;

    @Column(name = "list_type", nullable = false)
    private String listType;

}
