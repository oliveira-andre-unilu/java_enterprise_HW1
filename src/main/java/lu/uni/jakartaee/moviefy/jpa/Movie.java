package lu.uni.jakartaee.moviefy.jpa;


import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Stateless
@Entity(name="movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String director; // Will be changed to another entity class
    private String allActors; // One-to-many relationship afterwards
    private String genre;
    private int runtime;
    private int year;
    private String description;
    private String posterLocation;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
