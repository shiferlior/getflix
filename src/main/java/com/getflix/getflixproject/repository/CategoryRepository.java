package com.getflix.getflixproject.repository;

import com.getflix.getflixproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
