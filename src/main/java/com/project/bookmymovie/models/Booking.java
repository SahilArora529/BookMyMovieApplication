package com.project.bookmymovie.models;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "ticketId=" + ticketId +
                ", bookingDate=" + bookingDate +
                ", noOfSeats=" + noOfSeats +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}