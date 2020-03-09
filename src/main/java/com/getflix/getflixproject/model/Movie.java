package com.getflix.getflixproject.model;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "[movie]")
public class Movie {
    private int id;
    private String name;
    private String description;
    private String director;
    private int length;
    private int quantity;
    private Date insertionTime;
    private String image;

    public Movie() {

    }
    public Movie(String name,
             String description,
             String director,
             int length,
             int quantity,
             Date insertionTime,
             String image) {
        this.name = name;
        this.description = description;
        this.director = director;
        this.length = length;
        this.quantity = quantity;
        this.insertionTime = insertionTime;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "director", nullable = true)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    @Column(name = "length", nullable = true)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Column(name = "insertionTime", nullable = true)
    public Date getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(Date insertionTime) {
        this.insertionTime = insertionTime;
    }

    @Column(name = "image", nullable = true)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", _description=" + description + ", _director=" + director +
        " , _length" + length + ", _quantity" + quantity + ", _insertionTime" + insertionTime.toString() + "]";
    }
}
