package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.models.*;
import com.project.bookmymovie.repositories.BookingDao;
import com.project.bookmymovie.exceptions.MovieDetailsNotFoundException;
import com.project.bookmymovie.exceptions.UserDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    CinemaService cinemaINOXService;

    @Autowired
    CinemaService cinemaPVRService;

    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    @Autowired
    ScreenService screenService;

    @Autowired
    BookingDao bookingDao;

    @Override
    public Booking addTicketBooking(Booking booking) throws UserDetailsNotFoundException, MovieDetailsNotFoundException, CinemaDetailsNotFoundException, ScreenNotFoundException {
        //TODO to add the booking with movie, cinema, screen
        Cinema cinema = new Cinema();
        Screen screen = new Screen();
        Movie movie = movieService.getMovieDetailsByMovieTitle(booking.getMovie().getMovieTitle());
        User user = userService.getUserDetailsByUsername(booking.getUser().getUsername());
        if (booking.getMovie().getCinema().getCinemaType().equals("PVR")) {
            cinema = cinemaPVRService.getCinemaDetailsByName(movie.getCinema().getCinemaName());
        }
        if (movie.getCinema().getCinemaType().equals("INOX")) {
            cinema = cinemaINOXService.getCinemaDetailsByName(movie.getCinema().getCinemaName());
        }
        if (movie.getScreen().getScreenName().equals("UST") || movie.getScreen().getScreenName().equals("BalonEdge")) {
            screen = screenService.getScreenDetailsByName(movie.getScreen().getScreenName());
        }
        movie.setScreen(screen);
        movie.setCinema(cinema);
        booking.setMovie(movie);
        booking.setUser(user);
        bookingDao.save(booking);
        return booking;
    }

    @Override
    public List<Booking> getAllBookingDetails() {
        // TODO to get all bookings
        return bookingDao.findAllOrderedDescending();
    }
}

