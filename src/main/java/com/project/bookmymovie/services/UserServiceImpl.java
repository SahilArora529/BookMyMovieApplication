package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.InternalErrorException;
import com.project.bookmymovie.repositories.UserDao;
import com.project.bookmymovie.models.User;
import com.project.bookmymovie.exceptions.UserDetailsNotFoundException;
import com.project.bookmymovie.exceptions.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = InternalErrorException.class)
    @Override
    public User addUserDetails(User user) throws UserNameExistsException {
        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            throw new UserNameExistsException("This username is already taken.");
        }
        return userDao.save(user);
    }

    @Override
    public User getUserDetailsByUsername(String username) throws UserDetailsNotFoundException {
        return userDao.findByUsername(username)
                .orElseThrow(
                        () -> new UserDetailsNotFoundException("User not found with username: " + username)
                );
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
