package com.project.bookmymovie.services;

import com.project.bookmymovie.models.*;
import com.project.bookmymovie.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service("InitService")
public class InitServiceImpl implements InitService {
    @Autowired
    UserService userService;

    @Autowired
    CinemaINOXServiceImpl cinemaINOXService;

    @Autowired
    CinemaPVRServiceImpl cinemaPVRService;

    @Autowired
    MovieService movieService;

    @Autowired
    BookingService bookingService;

    @Autowired
    ScreenService screenService;

    List<String> statuses = Arrays.asList("Upcoming", "Released", "Blocked");

    User user = new User();
    Cinema cinema1 = new Cinema();
    Cinema cinema2 = new Cinema();
    Movie movie1 = new Movie();
    Movie movie2 = new Movie();
    Screen screen1 = new Screen();
    Screen screen2 = new Screen();

    public void addUser() throws UserNameExistsException {
        user.setFirstName("Emma");
        user.setLastName("Stone");
        user.setUsername("emma123stone");
        user.setPassword("emma@amme");
        user = userService.addUserDetails(user);
    }

    public void addCinema() throws CinemaDetailsNotFoundException, CinemaNameExistsException, ScreenNotFoundException {
        cinema1.setCinemaType("PVR");
        cinema1.setCinemaName("rudra");
        cinema1 = cinemaPVRService.addCinemaDetails(cinema1);

        cinema2.setCinemaType("INOX");
        cinema2.setCinemaName("Krishna");
        cinema2 = cinemaINOXService.addCinemaDetails(cinema2);
    }

    public void addScreens() throws  ScreenNameExistsException {
        screen1.setScreenName("UST");
        screen1 = screenService.addScreenDetails(screen1);
        screen2.setScreenName("BalonEdge");
        screen2 = screenService.addScreenDetails(screen2);
    }

    public void addMovies() throws CinemaDetailsNotFoundException, ScreenNotFoundException {
        movie1.setMovieTitle("InfinityWar");
        movie1.setScreen(screen1);
        movie1.setCinema(cinema1);
        movie1.setReleaseDate(LocalDateTime.of(2018, 4, 27, 5, 30));
        movie1.setStatus(statuses.get(1));
        movie1 = movieService.acceptMovieDetails(movie1);

        movie2.setMovieTitle("Endgame");
        movie2.setScreen(screen2);
        movie2.setCinema(cinema2);
        movie2.setReleaseDate(LocalDateTime.of(2019, 4, 26, 5, 30));
        movie2.setStatus(statuses.get(1));
        movie2 = movieService.acceptMovieDetails(movie2);
    }

    private void addBooking() throws UserDetailsNotFoundException, MovieDetailsNotFoundException, CinemaDetailsNotFoundException, ScreenNotFoundException {
        Booking booking = new Booking();
        booking.setBookingDate(LocalDateTime.of(2019, 1, 8, 0, 10));
        booking.setUser(user);
        booking.setNoOfSeats(150);
        booking.setMovie(movie1);
        booking.setMovie(movie2);
        bookingService.addTicketBooking(booking);
    }

    @Override
    public void init() throws UserNameExistsException, ScreenNotFoundException, CinemaDetailsNotFoundException, MovieDetailsNotFoundException, UserDetailsNotFoundException, ScreenNameExistsException, CinemaNameExistsException {
        addUser();
        addScreens();
        addCinema();
        addMovies();
        addBooking();

    }

}
