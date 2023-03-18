package com.project.bookmymovie.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name ="last_name", length = 20, nullable =false)
    private String lastName;

    @Column(name ="user_name", length = 20, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;
}
