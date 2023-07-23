package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.*;
import com.project.bookmymovie.models.*;
import com.project.bookmymovie.repositories.BookingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = {UserDetailsNotFoundException.class,MovieDetailsNotFoundException.class,CinemaDetailsNotFoundException.class,ScreenNotFoundException.class})
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

    @Transactional(readOnly = true)
    @Override
    public List<Booking> getAllBookingDetails() {
        // TODO to get all bookings
        return bookingDao.findAllOrderedDescending();
    }

    @Transactional(readOnly = true)
    @Override
    public Booking getBookingDetails(int id) throws BookingFailedException {
        return bookingDao.findById(id)
                .orElseThrow(
                        () -> new BookingFailedException("Booking not found for id: " + id)
                );
    }

    @Transactional(rollbackFor =BookingFailedException.class)
    @Override
    public Booking updateBookingDetails(int id,Booking booking) throws  BookingFailedException {
        Booking savedBooking= getBookingDetails(id);

        if (isNotNullOrZero(savedBooking.getMovie().getMovieTitle())) {
            savedBooking.setMovie(booking.getMovie());
        }
        if (isNotNullOrZero(savedBooking.getUser().getUsername())) {
            savedBooking.setUser(booking.getUser());
        }
        if (isNotNullOrZero(booking.getBookingDate())) {
            savedBooking.setBookingDate(booking.getBookingDate());
        }
        savedBooking.setNoOfSeats(booking.getNoOfSeats());
        return bookingDao.save(savedBooking);
    }

    @Transactional(rollbackFor = BookingFailedException.class)
    @Override
    public boolean deleteBooking(int id) throws BookingFailedException {
        Booking booking = getBookingDetails(id);
        bookingDao.delete(booking);
        return true;
    }

    private boolean isNotNullOrZero(Object obj) {
        return obj != null;
    }

}

