package com.getflix.getflixproject.model;

import javax.persistence.*;

@Entity
@Table(name = "[comment]")
public class Comment {
    private int id;
    private int userId;
    private int movieId;
    private String content;
    private float grade;

    public Comment() {}
    public Comment(int userId, int movieId, String content, float grade) {
        this.userId = userId;
        this.movieId = movieId;
        this.content = content;
        this.grade = grade;
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

    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "movieId", nullable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "grade")
    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
