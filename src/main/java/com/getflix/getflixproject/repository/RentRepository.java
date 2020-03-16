package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.Movie;
import com.getflix.getflixproject.model.Rent;
import com.getflix.getflixproject.model.RentNameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent,Integer> {
    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id" +
            "  WHERE u.[name] like %?1% AND m.[name] like %?2% AND isReturned=?5 AND r.[startDate] BETWEEN ?3 AND ?4"
            , nativeQuery = true)
    List<RentNameDetails> findRentsWithUserMovieIsReturn(String user, String movie, Date fromDate, Date toDate, Boolean isReturn);

    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id" +
            "  WHERE u.[name] like %?1% AND isReturned=?4 AND r.[startDate] BETWEEN ?2 AND ?3"
            , nativeQuery = true)
    List<RentNameDetails> findRentsWithUserIsReturn(String user, Date fromDate, Date toDate, Boolean isReturn);

    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id" +
            "  WHERE m.[name] like %?1% AND isReturned=?4 AND r.[startDate] BETWEEN ?2 AND ?3"
            , nativeQuery = true)
    List<RentNameDetails> findRentsWithMovieIsReturn(String movie, Date fromDate, Date toDate, Boolean isReturn);

    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id" +
            "  WHERE isReturned=?3 AND r.[startDate] BETWEEN ?1 AND ?2"
            , nativeQuery = true)
    List<RentNameDetails> findRentsWithIsReturn( Date fromDate, Date toDate, Boolean isReturn);

    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id" +
            "  WHERE u.[name] like %?1% AND m.[name] like %?2% AND r.[startDate] BETWEEN ?3 AND ?4"
            , nativeQuery = true)
    List<RentNameDetails> findRentsWithUserMovie(String user, String movie, Date fromDate, Date toDate);


    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id" +
            "  WHERE u.[name] like %?1% AND r.[startDate] BETWEEN ?2 AND ?3"
            , nativeQuery = true)
    List<RentNameDetails> findRentsWithUser(String user, Date fromDate, Date toDate);

    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id" +
            "  WHERE m.[name] like %?1% AND r.[startDate] BETWEEN ?2 AND ?3"
            , nativeQuery = true)
    List<RentNameDetails> findRentsWithMovie(String movie, Date fromDate, Date toDate);

    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id" +
            "  WHERE r.[startDate] BETWEEN ?1 AND ?2"
            , nativeQuery = true)
    List<RentNameDetails> findRents( Date fromDate, Date toDate);

    @Query(value = "SELECT r.[id]" +
            "      ,[userId]" +
            "      ,u.[name] as [userName]" +
            "      ,[movieId]" +
            "      ,m.[name] as [movieName]" +
            "      ,[startDate]" +
            "      ,[endDate]" +
            "      ,[isReturned]" +
            "  FROM [Getflix].[dbo].[rent] r" +
            "  JOIN [dbo].[user] u" +
            "  ON r.userId=u.id" +
            "  JOIN [dbo].[movie] m" +
            "  ON r.movieId=m.id"
            , nativeQuery = true)
    List<RentNameDetails> AllRents();

}
