package com.project.bookmymovie.repositories;

import com.project.bookmymovie.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {
    public List<Booking> findAllOrderedDescending();
}
