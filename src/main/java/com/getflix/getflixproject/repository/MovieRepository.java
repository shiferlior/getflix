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
            "  FROM movie as m \n" +
            "  WHERE m.[name] like %?1% AND" +
            "        ?2 in (SELECT DISTINCT mc.categoryId  from dbo.movieCategories mc \n" +
            "               where mc.movieId = m.id ) AND " +
            "       (SELECT COUNT(movieId)\n" +
            "        FROM dbo.rent\n" +
            "        WHERE movieId=m.id AND isReturned=0\n" +
            "        ) < m.quantity",
            nativeQuery = true)
    Optional<List<Movie>> findByMovieNameAndCategory(String movieName, Integer categoryId);


    @Query(value = "SELECT Distinct m.id\n" +
            "      ,m.name\n" +
            "      ,m.description\n" +
            "      ,m.director\n" +
            "      ,m.length\n" +
            "      ,m.quantity\n" +
            "      ,m.image\n" +
            "      ,m.insertionTime\n" +
            "  FROM movie as m \n" +
            "  WHERE m.[name] like %?1% AND " +
            "       (SELECT COUNT(movieId)\n" +
            "        FROM dbo.rent\n" +
            "        WHERE movieId=m.id AND isReturned=0\n" +
            "        ) < m.quantity",
            nativeQuery = true)
    Optional<List<Movie>> findByMovieName(String movieName);

    @Query(value = "SELECT Distinct m.id\n" +
            "      ,m.name\n" +
            "      ,m.description\n" +
            "      ,m.director\n" +
            "      ,m.length\n" +
            "      ,m.quantity\n" +
            "      ,m.image\n" +
            "      ,m.insertionTime\n" +
            "  FROM movie as m \n" +
            "  WHERE ?1 in (SELECT DISTINCT mc.categoryId  from dbo.movieCategories mc \n" +
            "               where mc.movieId = m.id ) AND " +
            "         (SELECT COUNT(movieId)\n" +
            "          FROM dbo.rent\n" +
            "          WHERE movieId=m.id AND isReturned=0\n" +
            "          ) < m.quantity",
            nativeQuery = true)
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
            "  ) < m.quantity", nativeQuery = true)
    Optional<List<Movie>> findMovies();

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
            "  ) < quantity AND m.id=?1", nativeQuery = true)
    Optional<List<Movie>> checkMovieQuantity(int movieId);
}