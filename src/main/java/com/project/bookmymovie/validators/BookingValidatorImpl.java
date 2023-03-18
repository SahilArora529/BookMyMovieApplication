package com.project.bookmymovie.validators;

import com.project.bookmymovie.models.Booking;
import com.project.bookmymovie.exceptions.APIException;
import com.project.bookmymovie.exceptions.BookingFailedException;
import com.project.bookmymovie.exceptions.MovieDetailsNotFoundException;
import com.project.bookmymovie.utils.DateDifference;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingValidatorImpl implements BookingValidator {


    @Override
    public void validateBooking(Booking booking) throws APIException, MovieDetailsNotFoundException, BookingFailedException {
        if (booking.getUser().getUsername() == null)
            throw new APIException("Invalid userName");
        if (booking.getNoOfSeats() <= 0)
            throw new APIException("Invalid number of seats");
        int dateDifference = DateDifference.differenceBetweenDates(booking.getBookingDate(), LocalDateTime.now());
        if (dateDifference < 0 || dateDifference >= 3)
            throw new APIException("Invalid booking date");
    }
}