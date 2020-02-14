package com.getflix.getflixproject;

import com.getflix.getflixproject.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

}