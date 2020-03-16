package com.getflix.getflixproject.controller;

import com.getflix.getflixproject.ResourceNotFoundException;
import com.getflix.getflixproject.model.Movie;
import com.getflix.getflixproject.model.Rent;
import com.getflix.getflixproject.model.RentNameDetails;
import com.getflix.getflixproject.model.RentQuery;
import com.getflix.getflixproject.repository.MovieRepository;
import com.getflix.getflixproject.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RentController {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/Rents")
    public List<RentNameDetails> getAllRents() {
        return rentRepository.AllRents();
    }


    @PostMapping("/Rents/Query")
    public List<RentNameDetails> queryRents(@Valid @RequestBody RentQuery rent) {
        String user = rent.getUser();
        String movie = rent.getMovie();
        java.sql.Date fromDate = rent.getFromDate();
        java.sql.Date toDate = rent.getToDate();
        String isReturn = rent.getIsReturn();

        if (!isReturn.isEmpty() && isReturn.equals("all")) {
            if (!user.isEmpty() && !movie.isEmpty())
                return rentRepository.findRentsWithUserMovie(user, movie, fromDate, toDate);
            else if (!user.isEmpty())
                return rentRepository.findRentsWithUser(user, fromDate, toDate);
            else if (!movie.isEmpty())
                return rentRepository.findRentsWithMovie(movie, fromDate, toDate);
            else
                return rentRepository.findRents(fromDate,toDate);
        } else {
            boolean isReturned = false;
            if (!isReturn.isEmpty() && isReturn.equals("yes"))
                isReturned = true;

            if (!user.isEmpty() && !movie.isEmpty())
                return rentRepository.findRentsWithUserMovieIsReturn(user, movie, fromDate, toDate, isReturned);
            else if (!user.isEmpty())
                return rentRepository.findRentsWithUserIsReturn(user, fromDate, toDate, isReturned);
            else if (!movie.isEmpty())
                return rentRepository.findRentsWithMovieIsReturn(movie, fromDate, toDate, isReturned);
            else
                return rentRepository.findRentsWithIsReturn(fromDate,toDate,isReturned);
        }
    }


    @PostMapping("/Rents")
    public Rent createRent(@Valid @RequestBody Rent rent)  throws ResourceNotFoundException {
        List<Movie> movieList = movieRepository.checkMovieQuantity(rent.getMovieId())
            .orElseThrow(() -> new ResourceNotFoundException("Can not submit rent for this movie, there is no movie to rent in quantity"));

        if(!movieList.isEmpty()) {
            return rentRepository.save(rent);
        }
        else {
            throw new ResourceNotFoundException("Can not submit rent for this movie, there is no movie to rent in quantity");
        }
    }

    @GetMapping("/rents/{id}")
    public ResponseEntity<Rent> getRentById(@PathVariable(value = "id") Integer rentId)
            throws ResourceNotFoundException {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new ResourceNotFoundException("Rent not found for this id :: " + rentId));
        return ResponseEntity.ok().body(rent);
    }

    @PutMapping("/rents/{id}")
    public ResponseEntity<Rent> updateEmployee(@PathVariable(value = "id") Integer rentId,
                                               @Valid @RequestBody Rent rentDetails) throws ResourceNotFoundException {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new ResourceNotFoundException("rent not found for this id :: " + rentId));

        rent.setMovieId(rentDetails.getMovieId());
        rent.setUserId(rentDetails.getUserId());
        rent.setStartDate(rentDetails.getStartDate());
        rent.setEndDate(rentDetails.getEndDate());
        rent.setReturned(rentDetails.isReturned());

        final Rent updatedEmployee = rentRepository.save(rent);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/rents/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer rentId)
            throws ResourceNotFoundException {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new ResourceNotFoundException("rent not found for this id :: " + rentId));

        rentRepository.delete(rent);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
