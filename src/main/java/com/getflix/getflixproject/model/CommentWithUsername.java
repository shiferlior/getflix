package com.getflix.getflixproject.model;

public interface CommentWithUsername {
    int getId();
    int getUserId();
    String getUsername();
    int getMovieId();
    String getContent();
    Float getGrade();
}
