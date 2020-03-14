package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "SELECT Distinct m.id\n" +
            "      ,m.name\n" +
            "      ,m.description\n" +
            "      ,m.director\n" +
            "      ,m.length\n" +
            "      ,m.quantity\n" +
            "      ,m.image\n" +
            "      ,m.insertionTime\n" +
            "  FROM movieCategories as mc\n" +
            "  join movie as m \n" +
            "  on m.id=mc.movieId\n" +
            "  WHERE mc.categoryId = ?1 AND (\n" +
            "  SELECT COUNT(movieId)\n" +
            "  FROM dbo.rent\n" +
            "  WHERE movieId=m.id AND isReturned=0\n" +
            "  ) < quantity", nativeQuery = true)
    Optional<List<Movie>> findByCategoryId(Integer categoryId);

    @Query(value = "SELECT Distinct m.id\n" +
            "      ,m.name\n" +
            "      ,m.description\n" +
            "      ,m.director\n" +
            "      ,m.length\n" +
            "      ,m.quantity\n" +
            "      ,m.image\n" +
            "      ,m.insertionTime\n" +
            "  FROM movie as m \n" +
            "  WHERE (\n" +
            "  SELECT COUNT(movieId)\n" +
            "  FROM dbo.rent\n" +
            "  WHERE movieId=m.id AND isReturned=0\n" +
            "  ) < quantity", nativeQuery = true)
    Optional<List<Movie>> findMovies();
}