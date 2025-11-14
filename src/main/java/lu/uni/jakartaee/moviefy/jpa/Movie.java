package lu.uni.jakartaee.moviefy.jpa;


import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name="movie")
@NamedQuery(name = "Movie.findAll", query = "SELECT m FROM movie m")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "director_id")
    private Director director;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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
    @Column(name = "poster_location")
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

    // getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterLocation() {
        return posterLocation;
    }

    public void setPosterLocation(String posterLocation) {
        this.posterLocation = posterLocation;
    }
}
