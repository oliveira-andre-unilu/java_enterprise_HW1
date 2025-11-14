package lu.uni.jakartaee.moviefy.jpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="director")
@NamedQuery(name = "Director.findAll", query = "SELECT d FROM director d")
public class Director implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "director", cascade = CascadeType.PERSIST)
    private List<Movie> movies;
    private int BornYear;

    //Constructors
    public Director() {
        this.name = "UNKNOWN";
        this.BornYear = 0;
        this.movies = new ArrayList<>();
    }

    public Director(String name, int bornYear) {
        this.name = name;
        this.BornYear = bornYear;
        this.movies = new ArrayList<>();
    }

    //Getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Director)) {
            return false;
        }
        Director other = (Director) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    // General methods
    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getBornYear() {
        return BornYear;
    }

    public void setBornYear(int bornYear) {
        BornYear = bornYear;
    }
}
