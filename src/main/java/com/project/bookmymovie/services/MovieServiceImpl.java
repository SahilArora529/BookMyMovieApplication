package com.project.bookmymovie.services;

import com.project.bookmymovie.models.Cinema;
import com.project.bookmymovie.models.Screen;
import com.project.bookmymovie.repositories.MovieDao;
import com.project.bookmymovie.models.Movie;
import com.project.bookmymovie.exceptions.MovieDetailsNotFoundException;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieDao movieDao;
    @Autowired
    CinemaINOXServiceImpl cinemaINOXService;
    @Autowired
    CinemaPVRServiceImpl cinemaPVRService;
    @Autowired
    ScreenService screenService;

    @Transactional(rollbackFor = {CinemaDetailsNotFoundException.class, ScreenNotFoundException.class})
    @Override
    public Movie acceptMovieDetails(Movie movie) throws CinemaDetailsNotFoundException, ScreenNotFoundException {
        //TODO to add the movies with cinema and screen
        String cinemaType = movie.getCinema().getCinemaType();
        Cinema cinema = new Cinema();
        if (movie.getCinema().getCinemaType().equals("PVR")) {
            cinema = cinemaPVRService.getCinemaDetailsByName(movie.getCinema().getCinemaName());
            String cinemaType1 = cinema.getCinemaType();
            if (cinemaType.equalsIgnoreCase(cinemaType1)) {
                cinemaType1 = cinemaType;
                cinema.setCinemaType(cinemaType1);
            } else {
                throw new CinemaDetailsNotFoundException("Cinema Type is not available");
            }
        }
        if (movie.getCinema().getCinemaType().equals("INOX")) {
            cinema = cinemaINOXService.getCinemaDetailsByName(movie.getCinema().getCinemaName());
            String cinemaType1 = cinema.getCinemaType();
            if (cinemaType.equalsIgnoreCase(cinemaType1)) {
                cinemaType1 = cinemaType;
                cinema.setCinemaType(cinemaType1);
            } else {
                throw new CinemaDetailsNotFoundException("Cinema Type is not available");
            }
        }
        Screen screen = screenService.getScreenDetailsByName(movie.getScreen().getScreenName());
        movie.setScreen(screen);
        movie.setCinema(cinema);
        return movieDao.save(movie);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAllMovies() {
        //TODO to find the all movies
        return movieDao.findAllOrderedDescending();
    }

    @Transactional(readOnly = true)
    @Override
    public Movie getMovieDetailsByMovieTitle(String movieTitle) throws MovieDetailsNotFoundException {
        //TODO to the movies by Title
        return movieDao.findByMovieTitle(movieTitle)
                .orElseThrow(
                        () -> new MovieDetailsNotFoundException("Movie not found with movieTitle: " + movieTitle)
                );
    }

    @Transactional(rollbackFor = MovieDetailsNotFoundException.class)
    @Override
    public Movie updateMovieDetails(String movieTitle, Movie movie) throws MovieDetailsNotFoundException, ScreenNotFoundException, CinemaDetailsNotFoundException {
        String cinemaType = movie.getCinema().getCinemaType();
        Movie savedMovie = getMovieDetailsByMovieTitle(movieTitle);
        if (isNotNullOrZero(movie.getMovieTitle())) {
            savedMovie.setMovieTitle(movie.getMovieTitle());
        }
        if (isNotNullOrZero(movie.getReleaseDate())) {
            savedMovie.setReleaseDate(movie.getReleaseDate());
        }
        if (isNotNullOrZero(movie.getStatus())) {
            savedMovie.setStatus(movie.getStatus());
        }
        if (isNotNullOrZero(movie.getScreen().getScreenName())) {
            Screen screen = screenService.getScreenDetailsByName(movie.getScreen().getScreenName());
            movie.setScreen(screen);
        }
        if (movie.getCinema().getCinemaType().equals("PVR")) {
            Cinema cinema = cinemaPVRService.getCinemaDetailsByName(movie.getCinema().getCinemaName());
            String cinemaType1 = cinema.getCinemaType();
            if (cinemaType.equalsIgnoreCase(cinemaType1)) {
                cinemaType1 = cinemaType;
                cinema.setCinemaType(cinemaType1);
            } else {
                throw new CinemaDetailsNotFoundException("Cinema Type is not available");
            }
        }
        if (movie.getCinema().getCinemaType().equals("INOX")) {
            Cinema cinema = cinemaINOXService.getCinemaDetailsByName(movie.getCinema().getCinemaName());
            String cinemaType1 = cinema.getCinemaType();
            if (cinemaType.equalsIgnoreCase(cinemaType1)) {
                cinemaType1 = cinemaType;
                cinema.setCinemaType(cinemaType1);
            } else {
                throw new CinemaDetailsNotFoundException("Cinema Type is not available");
            }
        }

        return movieDao.save(savedMovie);
    }

    @Transactional(rollbackFor = MovieDetailsNotFoundException.class)
    @Override
    public boolean deleteMovie(String movieTitle) throws MovieDetailsNotFoundException {
        Movie movie = getMovieDetailsByMovieTitle(movieTitle);
        movieDao.delete(movie);
        return true;
    }

    private boolean isNotNullOrZero(Object obj) {
        return obj != null;
    }


}


