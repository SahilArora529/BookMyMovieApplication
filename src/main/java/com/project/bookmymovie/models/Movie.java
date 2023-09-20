package com.project.bookmymovie.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Movie")
@NamedQuery(name = "Movie.findAllOrderedDescending",
        query = "SELECT c FROM Movie c ORDER BY c.movieId DESC")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "movie_title", length = 20, nullable = false)
    private String movieTitle;

    @Column(name ="release_date", length = 20, nullable = false, unique = true)
    private LocalDateTime releaseDate;

    @Column(name="status",nullable=false)
    private String status;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    public Movie() {
    }

    public Movie(int movieId, String movieTitle, LocalDateTime releaseDate, String status) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieId == movie.movieId && movieTitle.equals(movie.movieTitle) && releaseDate.equals(movie.releaseDate) && status.equals(movie.status) && screen.equals(movie.screen) && cinema.equals(movie.cinema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieTitle, releaseDate, status, screen, cinema);
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", releaseDate=" + releaseDate +
                ", status='" + status + '\'' +
                ", screen=" + screen +
                ", cinema=" + cinema +
                '}';
    }

}
