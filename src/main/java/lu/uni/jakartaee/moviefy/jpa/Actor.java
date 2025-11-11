package lu.uni.jakartaee.moviefy.jpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name="main_actor")
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_actor_relation",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id")
    )
    private List<Movie> movies;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
