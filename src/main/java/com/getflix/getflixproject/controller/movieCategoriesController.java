package com.getflix.getflixproject.controller;

import com.getflix.getflixproject.ResourceNotFoundException;
import com.getflix.getflixproject.model.Category;
import com.getflix.getflixproject.model.CategoryUpdate;
import com.getflix.getflixproject.model.MovieCategories;
import com.getflix.getflixproject.repository.MovieCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @GetMapping("/movieCategories/Movie/{id}")
    public List<MovieCategories> getByMovieId(@PathVariable(value = "id") Integer movieId)
            throws ResourceNotFoundException {
        return movieCategoriesRepository.findMovieCategoriesByMovieId(movieId);
    }

    @PostMapping("/movieCategories/Manage")
    public CategoryUpdate manageCategories(@Valid @RequestBody CategoryUpdate categoryUpdate){
        List<MovieCategories> movieCategories = movieCategoriesRepository.findMovieCategoriesByMovieId(categoryUpdate.getMovieId());
//        List<MovieCategories> movieCategories = new ArrayList<>();
//
//        //get only those of current movie
//        for(int i=0;i<movieCategoriesBefore.size();i++){
//            MovieCategories current = movieCategoriesBefore.get(i);
//            if(current.getMovieId()==categoryUpdate.getMovieId()){
//                movieCategories.add(current);
//            }
//        }

        int[] categoryIds = categoryUpdate.getCategoryIds();

        //delete irrelevant
        for(int i=0;i<movieCategories.size();i++){
            MovieCategories current = movieCategories.get(i);
            boolean found=false;
            for(int j=0;j<categoryIds.length;j++){
                if(current.getCategoryId() == categoryIds[j]){
                    found = true;
                    break;
                }
            }
            if(!found){
                movieCategoriesRepository.delete(current);
            }
        }

        //add new
        for(int i=0;i<categoryIds.length;i++){
            int currentCategoryId = categoryIds[i];
            boolean found=false;
            for(int j=0;j<movieCategories.size();j++){
                if(currentCategoryId == movieCategories.get(j).getCategoryId()){
                    found = true;
                    break;
                }
            }
            if(!found){
                MovieCategories newMovieCategories = new MovieCategories();
                newMovieCategories.setCategoryId(currentCategoryId);
                newMovieCategories.setMovieId(categoryUpdate.getMovieId());
                movieCategoriesRepository.save(newMovieCategories);
            }
        }
        return categoryUpdate;
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
