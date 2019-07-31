package com.obss.movietracker.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "director")
    private Director director;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "imdb_rating")
    private String imdbRating;

    @Column(name = "duration")
    private String duration;

    @Column(name = "movie_genre")
    private String movieGenre;

    @Column(name = "poster")
    private String poster;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<UserMovieList> userMovieLists;

    @Override
    public boolean equals(Object obj) {
        Movie movie = (Movie) obj;
        return id.equals(movie.getId());
    }

}
