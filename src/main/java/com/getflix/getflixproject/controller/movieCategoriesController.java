package com.getflix.getflixproject.controller;

import com.getflix.getflixproject.ResourceNotFoundException;
import com.getflix.getflixproject.model.MovieCategories;
import com.getflix.getflixproject.repository.MovieCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class movieCategoriesController {
    @Autowired
    private MovieCategoriesRepository movieCategoriesRepository;

    @GetMapping("/movieCategories")
    public List<MovieCategories> getAllMovieCategoriess() {
        return  movieCategoriesRepository.findAll();
    }

    @PostMapping("/movieCategories")
    public MovieCategories createMovieCategories(@Valid @RequestBody MovieCategories movieCategories){
        return movieCategoriesRepository.save(movieCategories);
    }

    @GetMapping("/movieCategories/{id}")
    public ResponseEntity<MovieCategories> getEmployeeById(@PathVariable(value = "id") Integer movieCategoriesId)
            throws ResourceNotFoundException {
        MovieCategories movieCategories = movieCategoriesRepository.findById(movieCategoriesId)
                .orElseThrow(() -> new ResourceNotFoundException("movieCategories not found for this id :: " + movieCategoriesId));
        return ResponseEntity.ok().body(movieCategories);
    }

    @PutMapping("/movieCategories/{id}")
    public ResponseEntity<MovieCategories> updateEmployee(@PathVariable(value = "id") Integer movieCategoriesId,
                                               @Valid @RequestBody MovieCategories movieCategoriesDetails) throws ResourceNotFoundException {
        MovieCategories movieCategories = movieCategoriesRepository.findById(movieCategoriesId)
                .orElseThrow(() -> new ResourceNotFoundException("movieCategories not found for this id :: " + movieCategoriesId));

        movieCategories.setMovieId(movieCategoriesDetails.getMovieId());
        movieCategories.setCategoryId(movieCategoriesDetails.getCategoryId());

        final MovieCategories updatedEmployee = movieCategoriesRepository.save(movieCategories);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/movieCategories/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer movieCategoriesId)
            throws ResourceNotFoundException {
        MovieCategories movieCategories = movieCategoriesRepository.findById(movieCategoriesId)
                .orElseThrow(() -> new ResourceNotFoundException("movieCategories not found for this id :: " + movieCategoriesId));

        movieCategoriesRepository.delete(movieCategories);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
