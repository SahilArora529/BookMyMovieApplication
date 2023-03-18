package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.APIException;
import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;
import com.project.bookmymovie.exceptions.ScreenNameExistsException;
import com.project.bookmymovie.models.Movie;
import com.project.bookmymovie.models.Screen;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;

import java.util.List;

public interface ScreenService {
    public Screen addScreenDetails(Screen screen) throws ScreenNameExistsException;
    public List<Screen> getAllScreens();
    public Screen getScreenDetailsByName(String screenName) throws ScreenNotFoundException;
}
