package com.getflix.getflixproject.controller;

import com.getflix.getflixproject.ResourceNotFoundException;
import com.getflix.getflixproject.model.Rent;
import com.getflix.getflixproject.model.RentNameDetails;
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

    @GetMapping("/Rents")
    public List<Rent> getAllRents() {
        return  rentRepository.findAll();
    }

    @GetMapping("/Rents/User/{User}/Movie/{Movie}/from/{FromDate}/to/{ToDate}/IsReturn/{IsReturn}")
    public List<RentNameDetails> getAllRentsBy(@PathVariable(value = "User") String user,
                                    @PathVariable(value = "Movie") String movie,
                                    @PathVariable(value = "FromDate") java.sql.Date fromDate,
                                    @PathVariable(value = "ToDate") java.sql.Date toDate,
                                    @PathVariable(value = "IsReturn") Boolean isReturn) {
        return  rentRepository.findRents(user,movie,fromDate,toDate,isReturn);
    }


    @PostMapping("/Rents")
    public Rent createRent(@Valid @RequestBody Rent rent){
        return rentRepository.save(rent);
    }

    @GetMapping("/rents/{id}")
    public ResponseEntity<Rent> getEmployeeById(@PathVariable(value = "id") Integer rentId)
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
