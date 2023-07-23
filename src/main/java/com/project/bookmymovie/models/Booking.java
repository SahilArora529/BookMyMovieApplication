package com.project.bookmymovie.models;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="Booking")
@NamedQuery(name = "Booking.findAllOrderedDescending",
        query = "SELECT c FROM Booking c ORDER BY c.ticketId DESC")
public class Booking implements Comparable<Booking> {

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

    public  Booking()
    {
    }

    public Booking(int ticketId, LocalDateTime bookingDate, int noOfSeats) {
        this.ticketId = ticketId;
        this.bookingDate = bookingDate;
        this.noOfSeats = noOfSeats;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return ticketId == booking.ticketId && noOfSeats == booking.noOfSeats && bookingDate.equals(booking.bookingDate) && user.equals(booking.user) && movie.equals(booking.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, bookingDate, noOfSeats, user, movie);
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

    @Override
    public int compareTo(Booking o) {
        if (this.ticketId < o.ticketId) {
            return -1;
        } else if (this.ticketId > o.ticketId) {
            return 1;
        } else {
            return 0;
        }
    }
    }
