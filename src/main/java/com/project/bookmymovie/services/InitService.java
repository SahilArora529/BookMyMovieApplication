package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.*;

public interface InitService {
    public void init() throws UserNameExistsException, ScreenNotFoundException, CinemaDetailsNotFoundException, MovieDetailsNotFoundException, UserDetailsNotFoundException, APIException, ScreenNameExistsException, CinemaNameExistsException;

}
