package com.project.bookmymovie.repositories;

import com.project.bookmymovie.exceptions.UserDetailsNotFoundException;
import com.project.bookmymovie.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository <User,Integer>{
public  Optional <User> findByUsername(String username) ;
}
