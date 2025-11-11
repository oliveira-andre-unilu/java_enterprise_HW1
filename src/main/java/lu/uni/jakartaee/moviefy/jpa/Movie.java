package lu.uni.jakartaee.moviefy.jpa;


import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

@Stateless
@Entity(name="movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Director director;
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
