package com.project.bookmymovie.repositories;

import com.project.bookmymovie.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenDao extends JpaRepository<Screen,Integer> {
    public Optional <Screen> findByScreenName(String username);
    public List<Screen> findAllOrderedDescending();
}
