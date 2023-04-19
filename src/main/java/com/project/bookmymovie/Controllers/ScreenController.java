package com.project.bookmymovie.Controllers;

import com.project.bookmymovie.exceptions.ScreenNameExistsException;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.models.Screen;
import com.project.bookmymovie.exceptions.APIException;
import com.project.bookmymovie.services.ScreenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie_app/v1")
public class ScreenController {

    @Autowired
    ScreenService screenService;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    // To add the screen
    @PostMapping(value = "/screen", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity addScreen(@RequestBody Screen screen, @RequestHeader(value = "ACCESS-TOKEN") String token) throws APIException, ScreenNameExistsException {
        if (token == null)
            throw new APIException("Please add proper authentication");
        Screen savedScreen = screenService.addScreenDetails(screen);
        return new ResponseEntity<>(savedScreen, HttpStatus.CREATED);

    }

    // To get view all movies and their Screen details available for a Cinema
    @GetMapping(value = "/screen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllScreen() {
        List<Screen> screenList = screenService.getAllScreens();
        logger.debug("Returning all movies", screenList);
        return new ResponseEntity<>(screenList, HttpStatus.OK);
    }

    // To update the screen details
    @PutMapping(value = "/screen/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateScreenDetails(@PathVariable(name = "name") String screenName, @RequestBody Screen screen, @RequestHeader(value = "ACCESS-TOKEN") String token) throws APIException, ScreenNotFoundException {
        logger.debug("Update movie details : movie id :" + screenName, screen);
        if (token == null)
            throw new APIException("Please add proper authentication");
        Screen updatedScreen = screenService.updateScreenDetails(screenName, screen);
        return new ResponseEntity<>(updatedScreen, HttpStatus.OK);
    }

    // To delete the screen details
    @DeleteMapping("/screen/{name}")
    public ResponseEntity<String> removeScreenDetails(@PathVariable("name") String screenName) throws ScreenNotFoundException {
        screenService.deleteScreen(screenName);
        return new ResponseEntity<>("Screen details successfully removed ", HttpStatus.OK);
    }
}
