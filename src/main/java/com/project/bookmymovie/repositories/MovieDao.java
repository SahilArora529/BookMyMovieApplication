package com.project.bookmymovie.repositories;

import com.project.bookmymovie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieDao extends JpaRepository<Movie,Integer> {
    public Optional<Movie> findByMovieTitle(String movieTitle);
    public List<Movie> findAllOrderedDescending();
}
