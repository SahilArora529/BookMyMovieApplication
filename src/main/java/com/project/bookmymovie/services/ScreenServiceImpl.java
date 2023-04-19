package com.project.bookmymovie.services;

import com.project.bookmymovie.exceptions.*;
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

    @Transactional(rollbackFor = ScreenNameExistsException.class)
    @Override
    public Screen addScreenDetails(Screen screen) throws ScreenNameExistsException {
        if (screendao.findByScreenName(screen.getScreenName()).isPresent()) {
            throw new ScreenNameExistsException("This ScreenName is already taken.");
        }
        return screendao.save(screen);
    }

    @Transactional(readOnly = true)
    @Override
    public Screen getScreenDetailsByName(String screenName) throws ScreenNotFoundException {
        return screendao.findByScreenName(screenName)
                .orElseThrow(
                        () -> new ScreenNotFoundException("Screen not found for ScreenName: " + screenName)
                );
    }

    @Transactional(readOnly = true)
    @Override
    public List<Screen> getAllScreens() {
        return screendao.findAllOrderedDescending();
    }

    @Transactional(rollbackFor = ScreenNameExistsException.class)
    @Override
    public  Screen updateScreenDetails(String  screenName, Screen screen) throws ScreenNotFoundException{
    Screen screen1= getScreenDetailsByName(screenName);
        if (isNotNullOrZero(screen1.getScreenName())) {
            screen1.setScreenName(screen.getScreenName());
        }
        return screendao.save(screen1);
    }

    @Transactional(rollbackFor = ScreenNameExistsException.class)
    @Override
    public boolean deleteScreen(String screenName) throws  ScreenNotFoundException {
        Screen screen = getScreenDetailsByName(screenName);
        screendao.delete(screen);
        return true;
    }

    private boolean isNotNullOrZero(Object obj) {
        return obj != null;
    }


}
