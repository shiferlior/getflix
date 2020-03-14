package com.getflix.getflixproject.controller;

import com.getflix.getflixproject.repository.MovieRepository;
import com.getflix.getflixproject.ResourceNotFoundException;
import com.getflix.getflixproject.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/")
    public String getServerHealth() {
        return "Good!";
    }

    @GetMapping("/Movies")
    public List<Movie> getAllMovies() throws ResourceNotFoundException {
        return  movieRepository.findMovies()
                .orElseThrow(() -> new ResourceNotFoundException("Movies not found for rent :: "));
    }

    @GetMapping("/Movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Integer movieId)
            throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping("/Movies/ByCategory/{id}")
    public List<Movie> geMovieByCategoryId(@PathVariable(value = "id") Integer categoryId)
            throws ResourceNotFoundException {
        return movieRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Movies not found for this category id :: " + categoryId));
    }

    @PostMapping("/Movies")
    public Movie createMovie(@Valid @RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @PutMapping("/Movies/{id}")
    public ResponseEntity<Movie> updateEmployee(@PathVariable(value = "id") Integer movieId,
                                                   @Valid @RequestBody Movie movieDetails) throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + movieId));

        movie.setName(movieDetails.getName());
        movie.setDescription(movieDetails.getDescription());
        movie.setDirector(movieDetails.getDirector());
        movie.setLength(movieDetails.getLength());
        movie.setLength(movieDetails.getLength());
        movie.setQuantity(movieDetails.getQuantity());
        movie.setImage(movieDetails.getImage());
        movie.setInsertionTime(movieDetails.getInsertionTime());

        final Movie updatedEmployee = movieRepository.save(movie);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/Movies/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer movieId)
            throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + movieId));

        movieRepository.delete(movie);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
