package com.project.bookmymovie.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

}
