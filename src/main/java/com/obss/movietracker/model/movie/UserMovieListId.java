package com.obss.movietracker.model.movie;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserMovieListId implements Serializable {
    Long movie;
    Long user;

//    @Override
//    public boolean equals(Object obj) {
//        UserMovieListId userMovieListId = (UserMovieListId) obj;
//        return (movie.equals(userMovieListId.movie) && user.equals(userMovieListId.user));
//    }
}
