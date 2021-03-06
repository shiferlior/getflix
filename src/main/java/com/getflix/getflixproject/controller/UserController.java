package com.getflix.getflixproject.controller;

import com.getflix.getflixproject.ResourceNotFoundException;
import com.getflix.getflixproject.repository.UserRepository;
import com.getflix.getflixproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/Users")
    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    @PostMapping("/Users")
    public User createUser(@Valid @RequestBody User user) throws ResourceNotFoundException {

        if(!userRepository.findUser(user.getName(),user.getPassword()).isPresent())
                return userRepository.save(user);
        else
            throw new ResourceNotFoundException("User is already register in Getflix, try to login");
    }

    @PostMapping("/Users/Login")
    public User loginUser(@Valid @RequestBody User user)  throws ResourceNotFoundException {
        return userRepository.findUser(user.getName(),user.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("User \'" + user.getName() + "\' not found"));
    }


    @GetMapping("/Users/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Integer userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/Users/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Integer userId,
                                                @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setName(userDetails.getName());
        user.setPassword(userDetails.getPassword());
        user.setAdmin(userDetails.isAdmin());

        final User updatedEmployee = userRepository.save(user);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/Users/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
