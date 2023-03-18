package com.project.bookmymovie.services;

import com.project.bookmymovie.models.User;
import com.project.bookmymovie.exceptions.UserDetailsNotFoundException;
import com.project.bookmymovie.exceptions.UserNameExistsException;

import java.util.List;

public interface UserService {
    public User addUserDetails(User user) throws UserNameExistsException;
    public User getUserDetailsByUsername(String username) throws UserDetailsNotFoundException;
    public List<User> getAllUsers();
}
