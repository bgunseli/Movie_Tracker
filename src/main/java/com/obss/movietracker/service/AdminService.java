package com.obss.movietracker.service;

import com.obss.movietracker.model.movie.Director;
import com.obss.movietracker.model.movie.Movie;
import com.obss.movietracker.model.user.User;
import com.obss.movietracker.repository.DirectorRepository;
import com.obss.movietracker.repository.MovieRepository;
import com.obss.movietracker.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AdminService {

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private DirectorRepository directorRepository;

    private static final String API_KEY = "6211a001";

    @Autowired
    public AdminService(MovieRepository movieRepository, UserRepository userRepository,
                        DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.directorRepository = directorRepository;
    }

    /**
     * Movies
     */

    public List<Movie> getMovies() {
        List<Movie> movieList = movieRepository.findAllByNameIsNot("");
        if (movieList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie");
        }
        return movieList;
    }

    public Movie getMovie(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This movie with given id doesn't exist");
        }
    }

    public void addMovie(Movie movie) {
        if (movie.getName() == null || movie.getName().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie's name cannot be empty");
        }
        if (movieRepository.existsByName(movie.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Movie already exist");
        }
        movieRepository.save(movie);
    }

    public void addMovieWithOmdb(String id) {
        String uri = "http://www.omdbapi.com/?i=[id]&apikey=[API_KEY]";
        uri = uri.replace("[API_KEY]", API_KEY);
        uri = uri.replace("[id]", id);
        try {
            JSONObject json = new JSONObject(IOUtils.toString(new URL(uri), Charset.forName("UTF-8")));
            Movie movie = new Movie();
            movie.setName(json.getString("Title"));
            if (movie.getName() == null || movie.getName().equals("")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie's name cannot be empty");
            }
            if (movieRepository.existsByName(movie.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Movie already exist");
            }
            SimpleDateFormat parser = new SimpleDateFormat("dd MMM yyyy", new Locale("en,EN"));
            Date date = parser.parse(json.getString("Released"));
            movie.setReleaseDate(date);
            movie.setImdbRating(json.getString("imdbRating"));
            movie.setDuration(json.getString("Runtime"));
            movie.setMovieGenre(json.getString("Genre"));
            movie.setPoster(json.getString("Poster"));

            Director director;
            String directorName = json.getString("Director");
            if (!directorRepository.existsByName(directorName)) {
                director = new Director();
                director.setName(directorName);
                directorRepository.save(director);
            }
            else {
                director = directorRepository.findByName(directorName);
            }
            movie.setDirector(director);
            movieRepository.save(movie);
        }
        catch (JSONException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This movie with given id doesn't exist");
        }
        movieRepository.deleteById(id);
    }

    public void updateMovie(Long id, Movie movie) {
        if (!movie.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given id and movie's id cannot be different");
        }
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This movie with given id doesn't exist");
        }
        movieRepository.save(movie);
    }

    /**
     * Users
     */

    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user with given id doesn't exist");
        }
    }

    public User getUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user with given id doesn't exist");
        }
    }

    public void addUser(User user) {
        if (user.getUsername() == null || user.getUsername().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User's name cannot be empty");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user with given id doesn't exist");
        }
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, User user) {
        if (!user.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given id and user's id cannot be different");
        }
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user with given id doesn't exist");
        }
        userRepository.save(user);
    }

    /**
     * Directors
     */

    public Director getDirector(Long id) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (optionalDirector.isPresent()) {
            return optionalDirector.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This director with given id doesn't exist");
        }
    }

    public void addDirector(Director director) {
        if (director.getName() == null || director.getName().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Director's name cannot be empty");
        }
        if (directorRepository.existsByName(director.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Director already exist");
        }
        directorRepository.save(director);
    }

    public void deleteDirector(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This director with given id doesn't exist");
        }
        directorRepository.deleteById(id);
    }

    public void updateDirector(Long id, Director director) {
        if (!director.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given id and director's id cannot be different");
        }
        if (!directorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This director with given id doesn't exist");
        }
        directorRepository.save(director);
    }
}
