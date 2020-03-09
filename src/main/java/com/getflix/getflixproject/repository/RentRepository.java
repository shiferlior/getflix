package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent,Integer> {
}
