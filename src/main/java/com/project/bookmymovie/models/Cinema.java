package com.project.bookmymovie.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

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

    public Cinema()
    {

    }

    public Cinema(int cinemaId, String cinemaName, String cinemaType) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.cinemaType = cinemaType;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaType() {
        return cinemaType;
    }

    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return cinemaId == cinema.cinemaId && cinemaName.equals(cinema.cinemaName) && cinemaType.equals(cinema.cinemaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaId, cinemaName, cinemaType);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaId=" + cinemaId +
                ", cinemaName='" + cinemaName + '\'' +
                ", cinemaType='" + cinemaType + '\'' +
                '}';
    }



}
