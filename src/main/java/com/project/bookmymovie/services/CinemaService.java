package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.CinemaNameExistsException;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.models.Cinema;
import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;

import java.util.List;

public interface CinemaService {
    public Cinema addCinemaDetails(Cinema cinema) throws CinemaNameExistsException, CinemaDetailsNotFoundException, ScreenNotFoundException;

    public List<Cinema> getCinemaDetailsByType(String cinemaType) throws CinemaDetailsNotFoundException;

    public Cinema getCinemaDetailsByName(String cinemaName) throws CinemaDetailsNotFoundException;

    public Cinema updateCinemaDetails(String cinemaName, Cinema cinema) throws CinemaDetailsNotFoundException;

    public boolean deleteCinema(String cinemaType ,String cinemaName) throws  CinemaDetailsNotFoundException;
}
