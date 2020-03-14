package com.getflix.getflixproject.controller;

import com.getflix.getflixproject.ResourceNotFoundException;
import com.getflix.getflixproject.model.Category;
import com.getflix.getflixproject.model.Movie;
import com.getflix.getflixproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/Categories")
    public List<Category> getAllMovies() {
        return categoryRepository.findAll();
    }

    @PostMapping("/Categories")
    public Category createMovie(@Valid @RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping("/Categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Integer categoryId)
            throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/Categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Integer categoryId,
                                                @Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + categoryId));

        category.setName(categoryDetails.getName());


        final Category updatedEmployee = categoryRepository.save(category);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/Categories/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Integer categoryId)
            throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

        categoryRepository.delete(category);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
