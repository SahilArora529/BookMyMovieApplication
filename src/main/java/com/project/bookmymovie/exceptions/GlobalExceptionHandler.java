package com.project.bookmymovie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserDetailsNotFoundException.class)
    public ResponseEntity<String> handleUserDetailsNotFoundException(UserDetailsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User details are not found");
    }

    @ExceptionHandler(CinemaDetailsNotFoundException.class)
    public ResponseEntity<String> handleCinemaDetailsNotFoundException(CinemaDetailsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Cinema details are not found");
    }

    @ExceptionHandler(ScreenNotFoundException.class)
    public ResponseEntity<String> handleScreenNotFoundException(ScreenNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Screen details are not found");
    }

    @ExceptionHandler(MovieDetailsNotFoundException.class)
    public ResponseEntity<String> handleMovieDetailsNotFoundException(MovieDetailsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Movie details are not found");
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> handleAPIException(APIException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("API not found");
    }

    @ExceptionHandler(BookingFailedException.class)
    public ResponseEntity<String> handleBookingFailedException(BookingFailedException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Bookings not found");
    }
}
