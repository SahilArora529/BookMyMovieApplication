package com.project.bookmymovie.services;


import com.project.bookmymovie.exceptions.ScreenNameExistsException;
import com.project.bookmymovie.models.Screen;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;

import java.util.List;

public interface ScreenService {

    public Screen addScreenDetails(Screen screen) throws ScreenNameExistsException;

    public List<Screen> getAllScreens();

    public Screen getScreenDetailsByName(String screenName) throws ScreenNotFoundException;

    public Screen updateScreenDetails(String screenName,Screen screen) throws ScreenNotFoundException;

    public boolean deleteScreen(String screenName) throws  ScreenNotFoundException;
}
