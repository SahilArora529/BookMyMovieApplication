package com.project.bookmymovie.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Cinema")
@Component("savedCinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id", insertable = false, updatable = false)
    private int cinemaId;

    @Column(name = "cinema_name", length = 20, nullable = false)
    private String cinemaName;

    @Column(name = "cinema_type", length = 20, nullable = false)
    private String cinemaType;

}
