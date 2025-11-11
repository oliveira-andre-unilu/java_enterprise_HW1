package lu.uni.jakartaee.moviefy.jpa;


import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Stateless
@Entity(name="movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @ManyToMany
    @JoinTable(
            name = "movie_actor_relation",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id")
    )
    private List<Actor> actors;
    private String genre;
    private int runtime;
    private int year;
    private String description;
    @JoinColumn(name = "poster_location")
    private String posterLocation;


    //Constructors
    public Movie(String title, Director director, List<Actor> actors, String genre, int runtime, int year, String description, String posterLocation) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.runtime = runtime;
        this.year = year;
        this.description = description;
        this.posterLocation = posterLocation;
    }

    public Movie() {
        this.title = "UNKNOWN";
        this.director = null;
        this.actors = null;
        this.genre = "UNKNOWN";
        this.runtime = 0;
        this.year = 0;
        this.description = "N/A";
        this.posterLocation = "N/A";
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
