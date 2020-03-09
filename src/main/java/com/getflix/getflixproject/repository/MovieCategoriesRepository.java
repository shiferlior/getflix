package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.MovieCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCategoriesRepository extends JpaRepository<MovieCategories,Integer> {
}
