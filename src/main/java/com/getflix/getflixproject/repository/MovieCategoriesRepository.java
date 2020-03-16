package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.MovieCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

import java.util.List;

public interface MovieCategoriesRepository extends JpaRepository<MovieCategories,Integer> {
    @Query(value = "select mc.* from dbo.movieCategories mc where mc.movieId  =  ?1", nativeQuery = true)
    List<MovieCategories> findMovieCategoriesByMovieId(Integer movieId);
}
