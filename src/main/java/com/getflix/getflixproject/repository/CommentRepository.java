package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.Comment;
import com.getflix.getflixproject.model.CommentWithUsername;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query(value = "SELECT c.[id] as id\n" +
            "      ,c.[userId]\n" +
            "      ,u.[name] as [userName]\n" +
            "      ,c.[movieId]\n" +
            "      ,c.[content] as content\n" +
            "      ,c.[grade]\n" +
            "  FROM [dbo].[comment] c\n" +
            "  JOIN [dbo].[user] u\n" +
            "  ON c.userId=u.id\n" +
            "  where movieId=?1", nativeQuery = true)
    List<CommentWithUsername> findByMovieId(Integer movieId);

}
