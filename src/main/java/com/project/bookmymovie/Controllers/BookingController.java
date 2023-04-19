package com.project.bookmymovie.Controllers;

import com.project.bookmymovie.models.Booking;
import com.project.bookmymovie.exceptions.*;
import com.project.bookmymovie.services.BookingService;
import com.project.bookmymovie.validators.BookingValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value="/movie_app/v1")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    BookingValidator bookingValidator;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    // All tickets booked earlier
    @GetMapping(value="/bookings",produces= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllBookings() {
        List<Booking> bookings = bookingService.getAllBookingDetails();
        logger.debug("Returning all movies", bookings);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // To book the  new ticket
    @PostMapping(value="/bookings",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTicketBooking(@RequestBody Booking booking, @RequestHeader(value = "ACCESS-TOKEN") String token) throws UserDetailsNotFoundException, MovieDetailsNotFoundException, APIException, BookingFailedException, CinemaDetailsNotFoundException, ScreenNotFoundException {
        if (token == null)
            throw new APIException("Please add proper authentication");
        bookingValidator.validateBooking(booking);
        Booking savedBooking = bookingService.addTicketBooking(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    // To update the booking details
    @PutMapping(value = "/bookings/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBookingDetails(@PathVariable(name = "id") int id, @RequestBody Booking booking, @RequestHeader(value = "ACCESS-TOKEN") String token) throws APIException, ScreenNotFoundException, MovieDetailsNotFoundException, BookingFailedException {
        logger.debug("Update booking details :" + id, booking);
        if (token == null)
            throw new APIException("Please add proper authentication");
        Booking updatedBooking = bookingService.updateBookingDetails(id, booking);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    // To delete booking
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> removeBookingDetails(@PathVariable("id") int id) throws BookingFailedException{
        bookingService.deleteBooking(id);
        return new ResponseEntity<>("Booking details successfully removed ",HttpStatus.OK);
    }
}
