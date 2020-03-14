package com.getflix.getflixproject.controller;

import com.getflix.getflixproject.ResourceNotFoundException;
import com.getflix.getflixproject.model.Comment;
import com.getflix.getflixproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/Comments")
    public List<Comment> getAllMovies() {
        return  commentRepository.findAll();
    }

    @PostMapping("/Comments")
    public Comment createComment(@Valid @RequestBody Comment comment){
        return commentRepository.save(comment);
    }

    @GetMapping("/Comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Integer commentId)
            throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment not found for this id :: " + commentId));
        return ResponseEntity.ok().body(comment);
    }

    @GetMapping("/Comments/ByMovieId/{id}")
    public List<Comment> getCommentByMovieId(@PathVariable(value = "id") Integer movieId)
            throws ResourceNotFoundException {
        return commentRepository.findByMovieId(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("comment not found for this movie id :: " + movieId));
    }

    @PutMapping("/Comments/{id}")
    public ResponseEntity<Comment> updatecomment(@PathVariable(value = "id") Integer commentId,
                                                   @Valid @RequestBody Comment commentDetails) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + commentId));

        comment.setContent(commentDetails.getContent());
        comment.setGrade(commentDetails.getGrade());
        comment.setMovieId(commentDetails.getMovieId());
        comment.setUserId(commentDetails.getUserId());


        final Comment updatedEmployee = commentRepository.save(comment);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/Comments/{id}")
    public Map<String, Boolean> deleteComment(@PathVariable(value = "id") Integer commentId)
            throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found for this id :: " + commentId));

        commentRepository.delete(comment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
