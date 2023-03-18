package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.*;
import com.project.bookmymovie.models.Cinema;
import com.project.bookmymovie.models.Movie;
import com.project.bookmymovie.repositories.ScreenDao;
import com.project.bookmymovie.models.Screen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    ScreenDao screendao;

    @Transactional(rollbackFor = InternalErrorException.class)
    @Override
    public Screen addScreenDetails(Screen screen) throws ScreenNameExistsException {
        if (screendao.findByScreenName(screen.getScreenName()).isPresent()) {
            throw new ScreenNameExistsException("This ScreenName is already taken.");
        }
        return screendao.save(screen);
    }

    @Override
    public Screen getScreenDetailsByName(String screenName) throws ScreenNotFoundException {
        return screendao.findByScreenName(screenName)
                .orElseThrow(
                        () -> new ScreenNotFoundException("Screen not found for ScreenName: " + screenName)
                );
    }

    @Override
    public List<Screen> getAllScreens() {
        return screendao.findAllOrderedDescending();
    }


}
