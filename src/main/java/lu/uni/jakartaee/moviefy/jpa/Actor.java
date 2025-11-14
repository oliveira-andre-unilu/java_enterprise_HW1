package lu.uni.jakartaee.moviefy.jpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name="main_actor")
@NamedQuery(name = "Actor.findAll", query = "SELECT a FROM main_actor a")
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movie_actor_relation",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id")
    )
    private List<Movie> movies;

    //Constructors
    public Actor(String name, List<Movie> movies) {
        this.name = name;
        this.movies = movies;
    }

    public Actor() {

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    //General functions
    public boolean addMovie(Movie movie) {
        if (this.movies.contains(movie)) {
            return false;
        }else{
            this.movies.add(movie);
            return true;
        }
    }

    //Getters and setters

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
