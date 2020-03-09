package com.getflix.getflixproject.model;

import javax.persistence.*;

@Entity
@Table(name = "[movieCategories]")
public class MovieCategories {
    private int id;
    private int movieId;
    private int categoryId;

    public MovieCategories(){ }
    public MovieCategories(int movieId,int categoryId) {
        this.movieId = movieId;
        this.categoryId = categoryId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "movieId", nullable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "categoryId", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


}
