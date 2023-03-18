package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.models.Booking;
import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;
import com.project.bookmymovie.exceptions.MovieDetailsNotFoundException;
import com.project.bookmymovie.exceptions.UserDetailsNotFoundException;

import java.util.List;

public interface BookingService {
    public Booking addTicketBooking(Booking booking) throws CinemaDetailsNotFoundException, UserDetailsNotFoundException, MovieDetailsNotFoundException, ScreenNotFoundException;
    public List<Booking> getAllBookingDetails();
}
