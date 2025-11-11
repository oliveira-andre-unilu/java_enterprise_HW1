package lu.uni.jakartaee.moviefy.jpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name="director")
public class Director implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Movie> movies;
    private int BornYear;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
