package com.project.bookmymovie.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@NamedQuery(name = "Screen.findAllOrderedDescending",
        query = "SELECT c FROM Screen c ORDER BY c.screenId DESC")
@Component("screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id",insertable = false, updatable = false)
    private int screenId;

    @Column(name = "screen_name", nullable = false)
    private String screenName;

}
