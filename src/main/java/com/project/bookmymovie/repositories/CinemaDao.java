package com.project.bookmymovie.repositories;

import com.project.bookmymovie.exceptions.CinemaDetailsNotFoundException;
import com.project.bookmymovie.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaDao extends JpaRepository<Cinema, Integer> {
  public  List<Cinema> findByCinemaType(String cinemaType) throws CinemaDetailsNotFoundException;
  public Optional<Cinema> findByCinemaName(String cinemaName) throws CinemaDetailsNotFoundException;
}
