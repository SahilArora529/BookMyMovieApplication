package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.*;
import com.project.bookmymovie.models.Booking;

import java.util.List;

public interface BookingService {

    public Booking addTicketBooking(Booking booking) throws CinemaDetailsNotFoundException, UserDetailsNotFoundException, MovieDetailsNotFoundException, ScreenNotFoundException;

    public List<Booking> getAllBookingDetails();

    public Booking getBookingDetails(int id) throws BookingFailedException;

    public boolean deleteBooking(int id) throws BookingFailedException;

    public Booking updateBookingDetails(int id, Booking booking) throws MovieDetailsNotFoundException, BookingFailedException;
}
