package com.project.bookmymovie.validators;

import com.project.bookmymovie.models.Booking;
import com.project.bookmymovie.exceptions.APIException;
import com.project.bookmymovie.exceptions.BookingFailedException;
import com.project.bookmymovie.exceptions.MovieDetailsNotFoundException;

public interface BookingValidator {
    public void validateBooking(Booking bookingDTO) throws APIException, MovieDetailsNotFoundException, BookingFailedException;
}
