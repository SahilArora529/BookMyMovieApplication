package com.project.bookmymovie.services;

import com.project.bookmymovie.models.Movie;
import com.project.bookmymovie.exceptions.MovieDetailsNotFoundException;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;

import java.util.List;

public interface MovieService {
    public Movie acceptMovieDetails(Movie movie)throws CinemaDetailsNotFoundException, ScreenNotFoundException;
    public List<Movie> getAllMovies();
    public Movie getMovieDetailsByMovieTitle(String movieTitle) throws MovieDetailsNotFoundException;
}
