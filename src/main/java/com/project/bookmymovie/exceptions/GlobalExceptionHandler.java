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
                .body(e.getMessage());
    }

    @ExceptionHandler(CinemaDetailsNotFoundException.class)
    public ResponseEntity<String> handleCinemaDetailsNotFoundException(CinemaDetailsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(ScreenNotFoundException.class)
    public ResponseEntity<String> handleScreenNotFoundException(ScreenNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(MovieDetailsNotFoundException.class)
    public ResponseEntity<String> handleMovieDetailsNotFoundException(MovieDetailsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> handleAPIException(APIException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(BookingFailedException.class)
    public ResponseEntity<String> handleBookingFailedException(BookingFailedException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(UserNameExistsException.class)
    public ResponseEntity<String> handleUserNameExistsException(UserNameExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<String> handleInternalErrorException(InternalErrorException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(ScreenNameExistsException.class)
    public ResponseEntity<String> handleScreenNameExistsException(ScreenNameExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(CinemaNameExistsException.class)
    public ResponseEntity<String> handleCinemaNameExistsException(CinemaNameExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }



}
