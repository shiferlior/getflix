package com.getflix.getflixproject;

import com.getflix.getflixproject.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/")
    public String getServerHealth() {
        return "Good!";
    }

    @GetMapping("/Movies")
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    @PostMapping("/Movies")
    public Movie createMovie(@Valid @RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @GetMapping("/Movies/{id}")
    public ResponseEntity<Movie> getEmployeeById(@PathVariable(value = "id") Integer movieId)
            throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + movieId));
        return ResponseEntity.ok().body(movie);
    }

    @PostMapping("/Movies")
    public Movie createEmployee(@Valid @RequestBody Movie movie) {
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
