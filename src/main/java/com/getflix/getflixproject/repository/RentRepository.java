package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.Rent;
import com.getflix.getflixproject.model.RentNameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

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
            "  WHERE u.[name]=?1 AND m.[name]=?2 AND isReturned=?5 AND r.[startDate] BETWEEN ?3 AND ?4"
            , nativeQuery = true)
    List<RentNameDetails> findRents(String user, String movie, Date fromDate, Date toDate, Boolean isReturn);
}
