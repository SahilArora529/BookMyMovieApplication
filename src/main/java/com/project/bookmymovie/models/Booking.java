package com.project.bookmymovie.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Booking")
@NamedQuery(name = "Booking.findAllOrderedDescending",
        query = "SELECT c FROM Booking c ORDER BY c.ticketId DESC")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int ticketId;

    @Column(name="booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column( nullable = false)
    private int noOfSeats;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

}