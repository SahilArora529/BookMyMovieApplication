package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.UserDetailsNotFoundException;
import com.project.bookmymovie.repositories.UserDao;
import com.project.bookmymovie.models.User;
import com.project.bookmymovie.exceptions.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = UserNameExistsException.class)
    @Override
    public User addUserDetails(User user) throws UserNameExistsException {
        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            throw new UserNameExistsException("This username is already taken.");
        }
        return userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserDetailsByUsername(String username) throws UserDetailsNotFoundException {
        return userDao.findByUsername(username)
                .orElseThrow(
                       () -> new UserDetailsNotFoundException("User not found with username: " + username)
                );
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional(rollbackFor = UserDetailsNotFoundException.class)
    @Override
    public boolean deleteUser(String userName) throws UserDetailsNotFoundException {
        User user = getUserDetailsByUsername(userName);
        userDao.delete(getUserById(user.getUserId()));
        return true;
    }
    @Transactional(rollbackFor = UserDetailsNotFoundException.class)
    @Override
    public  User updateUserDetails(String  userName, User user) throws UserDetailsNotFoundException{
        User user1= getUserDetailsByUsername(userName);
        if (isNotNullOrZero(user1.getFirstName())) {
            user1.setFirstName(user.getFirstName());
        }
        if (isNotNullOrZero(user1.getLastName())) {
            user1.setLastName(user.getLastName());
        }
        if (isNotNullOrZero(user1.getUsername())) {
            user1.setUsername(user.getUsername());
        }
        if (isNotNullOrZero(user1.getPassword())) {
            user1.setPassword(user.getPassword());
        }
        return userDao.save(user1);
    }
    @Transactional(readOnly = true)
    @Override
    public User getUserById(int id) throws UserDetailsNotFoundException {
        return userDao.findById(id)
                .orElseThrow(
                        () -> new UserDetailsNotFoundException("User not found with userId: " + id)
                );
    }

    private boolean isNotNullOrZero(Object obj) {
        return obj != null;
    }
}
