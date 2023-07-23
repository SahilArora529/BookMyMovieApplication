package com.project.bookmymovie.Controllers;

import com.project.bookmymovie.exceptions.CinemaNameExistsException;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.models.Cinema;
import com.project.bookmymovie.exceptions.APIException;
import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;
import com.project.bookmymovie.services.CinemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/movie_app/v1")
public class CinemaController {

    @Autowired
    CinemaService cinemaINOXService;

    @Autowired
    CinemaService cinemaPVRService;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    // To get the Cinema Details by type
    @GetMapping(value = "/cinema/{type}")
    public ResponseEntity getCinemaDetails(@PathVariable(name = "type") String type) throws CinemaDetailsNotFoundException {
        List<Cinema> savedCinema = new ArrayList<>();
        if (type.equalsIgnoreCase("PVR")) {
            savedCinema = cinemaPVRService.getCinemaDetailsByType(type);
        }
        if (type.equalsIgnoreCase("INOX")) {
            savedCinema = cinemaINOXService.getCinemaDetailsByType(type);
        }
        logger.debug("Returning all cinemas", savedCinema);
        return new ResponseEntity<>(savedCinema, HttpStatus.OK);
    }

    // To add the cinema
    @PostMapping(value = "/cinema", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity addCinema(@RequestBody Cinema cinema, @RequestHeader(value = "ACCESS-TOKEN") String token) throws APIException, CinemaDetailsNotFoundException, CinemaNameExistsException, ScreenNotFoundException {
        if (token == null)
            throw new APIException("Please add proper authentication");
        if (cinema.getCinemaType().equalsIgnoreCase("PVR")) {
            cinema = cinemaPVRService.addCinemaDetails(cinema);
        } else if (cinema.getCinemaType().equalsIgnoreCase("INOX")) {
            cinema = cinemaINOXService.addCinemaDetails(cinema);
        } else {
            throw new APIException("Cinema Type must be either INOX or PVR");
        }
        return new ResponseEntity<>(cinema, HttpStatus.CREATED);
    }

    // To update the cinema details
    @PutMapping(value = "/cinema/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCinemaDetails(@PathVariable(name = "name") String cinemaName, @RequestBody Cinema cinema, @RequestHeader(value = "ACCESS-TOKEN") String token) throws APIException, CinemaDetailsNotFoundException {
        logger.debug("Update cinema details :" + cinemaName, cinema);
        if (token == null)
            throw new APIException("Please add proper authentication");
        if (cinema.getCinemaType().equalsIgnoreCase("PVR")) {
            cinema = cinemaPVRService.updateCinemaDetails(cinemaName, cinema);
        } else if (cinema.getCinemaType().equalsIgnoreCase("INOX")) {
            cinema = cinemaINOXService.updateCinemaDetails(cinemaName, cinema);
        } else {
            throw new APIException("Cinema Type must be either INOX or PVR");
        }
        return new ResponseEntity<>(cinema, HttpStatus.CREATED);
    }

    // To delete the cinema details
    @DeleteMapping("/cinema/{type}/{name}")
    public ResponseEntity<String> removeCinemaDetails(@PathVariable("type") String cinemaType, @PathVariable("name") String cinemaName) throws CinemaDetailsNotFoundException {
        if (cinemaType.equalsIgnoreCase("PVR")) {
            cinemaPVRService.deleteCinema(cinemaType, cinemaName);
        }
        if (cinemaType.equalsIgnoreCase("INOX")) {
            cinemaPVRService.deleteCinema(cinemaType, cinemaName);
        }
        return new ResponseEntity<>("Cinema details successfully removed ", HttpStatus.OK);
    }


}
