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
}
