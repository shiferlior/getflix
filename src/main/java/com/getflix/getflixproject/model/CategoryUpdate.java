package com.getflix.getflixproject.model;

import javax.persistence.*;

public class CategoryUpdate {
    private int movieId;
    private int[] categoryIds;

    public CategoryUpdate() {

    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int[] getCategoryIds() {
        return this.categoryIds;
    }

    public void setCategoryIds(int[] categoryIds) {
        this.categoryIds = categoryIds;
    }


}
