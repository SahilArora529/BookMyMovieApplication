package com.project.bookmymovie.Controllers;

import com.project.bookmymovie.exceptions.MovieDetailsNotFoundException;
import com.project.bookmymovie.models.Movie;
import com.project.bookmymovie.exceptions.APIException;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;
import com.project.bookmymovie.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie_app/v1")
public class MovieController {

    @Autowired
    MovieService movieService;


    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    // To add the movies
    @PostMapping(value = "/movies", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity addMovie(@RequestBody Movie movie, @RequestHeader(value = "ACCESS-TOKEN") String token) throws APIException, CinemaDetailsNotFoundException, ScreenNotFoundException {
        if (token == null)
            throw new APIException("Please add proper authentication");
        Movie savedMovie = movieService.acceptMovieDetails(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    // To get view all movies and their Screen details available for a Cinema
    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMovies() {
        List<Movie> movieList = movieService.getAllMovies();
        logger.debug("Returning all movies", movieList);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    // To update the movie details
    @PutMapping(value = "/movie/{movieTitle}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMovieDetails(@PathVariable(name = "movieTitle") String movieTitle, @RequestBody Movie movie, @RequestHeader(value = "ACCESS-TOKEN") String token) throws APIException, ScreenNotFoundException, MovieDetailsNotFoundException,CinemaDetailsNotFoundException {
        logger.debug("Update movie details : " + movie);
        if (token == null)
            throw new APIException("Please add proper authentication");
        Movie updatedMovie = movieService.updateMovieDetails(movieTitle, movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    // To delete the movie details
    @DeleteMapping("/movie/{movieTitle}")
    public ResponseEntity<String> removeMovieDetails(@PathVariable("movieTitle") String movieTitle) throws MovieDetailsNotFoundException {
        movieService.deleteMovie(movieTitle);
        return new ResponseEntity<>("Movie details successfully removed ", HttpStatus.OK);
    }

}
