package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query(value = "SELECT * FROM [comment] WHERE movieId = ?1", nativeQuery = true)
    Optional<List<Comment>> findByMovieId(Integer movieId);
}
