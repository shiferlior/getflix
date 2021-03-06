package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT [id]" +
            ",[name]" +
            ",[password]" +
            ",[isAdmin]" +
            "FROM [Getflix].[dbo].[user]" +
            "where [name] =?1 AND [password]=?2"
            , nativeQuery = true)
    Optional<User> findUser(String userName, String password);
}
