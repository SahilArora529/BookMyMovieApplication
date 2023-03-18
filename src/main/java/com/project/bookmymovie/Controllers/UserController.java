package com.project.bookmymovie.Controllers;

import com.project.bookmymovie.dto.LoginDto;
import com.project.bookmymovie.models.User;
import com.project.bookmymovie.exceptions.*;
import com.project.bookmymovie.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/movie_app/v1")
public class UserController {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    // User signup
    @PostMapping(value = "/signup",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signup(@RequestBody User user)throws APIException, UserNameExistsException
    {
        User savedUser = userService.addUserDetails(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // User Login
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) throws UserDetailsNotFoundException,BadCredentialsException {
        Map<String, String> model = new HashMap<>();
        User savedUser = userService.getUserDetailsByUsername(loginDto.getUsername());
        if (!savedUser.getPassword().equals(loginDto.getPassword())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        model.put("message","Logged in Successfully");
        model.put("token",savedUser.getUsername());
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    // To get the all users
    @GetMapping(value="/users",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllUsers() {
        List<User> userList = userService.getAllUsers();
        logger.debug("Returning all users" , userList);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
