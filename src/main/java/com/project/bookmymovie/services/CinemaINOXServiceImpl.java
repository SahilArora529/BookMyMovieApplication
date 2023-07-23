package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.*;
import com.project.bookmymovie.models.Screen;
import com.project.bookmymovie.repositories.CinemaDao;
import com.project.bookmymovie.models.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Component("cinemaINOXService")
public class CinemaINOXServiceImpl implements CinemaService {
    @Autowired
    CinemaDao cinemaDao;

    @Transactional(rollbackFor = {CinemaNameExistsException.class, CinemaDetailsNotFoundException.class})
    @Override
    public Cinema addCinemaDetails(Cinema cinema) throws CinemaNameExistsException, CinemaDetailsNotFoundException {
        //TODO to add the cinema for INOX
        if (cinemaDao.findByCinemaName(cinema.getCinemaName()).isPresent()) {
            throw new CinemaNameExistsException("This CinemaName is already taken.");
        }
        return cinemaDao.save(cinema);
    }

    @Transactional(readOnly = true)
    public Cinema getCinemaDetailsByName(String cinemaName) throws CinemaDetailsNotFoundException {
        //TODO to get the cinema with cinema type
        return cinemaDao.findByCinemaName(cinemaName)
                .orElseThrow(
                        () -> new CinemaDetailsNotFoundException("Cinema not found for cinemaName: " + cinemaName)
                );
    }

    @Transactional(readOnly = true)
    public List<Cinema> getCinemaDetailsByType(String cinemaType) throws CinemaDetailsNotFoundException {
        //TODO to get the cinema with cinema type
        return cinemaDao.findByCinemaType(cinemaType);
    }

    @Transactional(rollbackFor = CinemaDetailsNotFoundException.class)
    @Override
    public Cinema updateCinemaDetails(String  cinemaName, Cinema cinema) throws CinemaDetailsNotFoundException {
        Cinema cinema1= getCinemaDetailsByName(cinemaName);
        if (isNotNullOrZero(cinema1.getCinemaName())) {
            cinema1.setCinemaName(cinema.getCinemaName());
        }
        if (isNotNullOrZero(cinema1.getCinemaType())) {
            cinema1.setCinemaType(cinema.getCinemaType());
        }
        return cinemaDao.save(cinema1);
    }

    @Transactional(rollbackFor = CinemaDetailsNotFoundException.class)
    @Override
    public boolean deleteCinema(String cinemaType,String cinemaName) throws  CinemaDetailsNotFoundException {
        List<Cinema> cinema = getCinemaDetailsByType(cinemaType);
        Cinema cinema1 = getCinemaDetailsByName(cinemaName);
        for (Cinema str: cinema)
        {
            if(str.getCinemaName().equals(cinema1.getCinemaName()))
            {
                cinemaDao.delete(cinema1);
            }
        }

        return true;
    }

    private boolean isNotNullOrZero(Object obj) {
        return obj != null;
    }

}
