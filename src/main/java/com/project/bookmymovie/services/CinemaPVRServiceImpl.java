package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.CinemaNameExistsException;
import com.project.bookmymovie.exceptions.InternalErrorException;
import com.project.bookmymovie.exceptions.ScreenNotFoundException;
import com.project.bookmymovie.models.Screen;
import com.project.bookmymovie.repositories.CinemaDao;
import com.project.bookmymovie.models.Cinema;
import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Component("cinemaPVRService")
public class CinemaPVRServiceImpl implements CinemaService {
    @Autowired
    CinemaDao cinemaDao;

    @Transactional(rollbackFor = InternalErrorException.class)
    @Override
    public Cinema addCinemaDetails(Cinema cinema) throws CinemaNameExistsException, CinemaDetailsNotFoundException, ScreenNotFoundException {
        //TODO to add the cinema for PVR
        if (cinemaDao.findByCinemaName(cinema.getCinemaName()).isPresent()) {
            throw new CinemaNameExistsException("This CinemaName is already taken.");
        }
        return cinemaDao.save(cinema);
    }

    public Cinema getCinemaDetailsByName(String cinemaName) throws CinemaDetailsNotFoundException {
        //TODO to find the cinema with cinema Name
        return cinemaDao.findByCinemaName(cinemaName)
                .orElseThrow(
                        () -> new CinemaDetailsNotFoundException("Cinema not found for cinemaName: " + cinemaName)
                );
    }

    public List<Cinema> getCinemaDetailsByType(String cinemaType) throws CinemaDetailsNotFoundException {
        //TODO to find the cinema with cinema type
        return cinemaDao.findByCinemaType(cinemaType);
    }
}
