package lu.uni.jakartaee.moviefy.jpa;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Named("client")
@SessionScoped
@Transactional
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static final long serialVersionUID = 1L;

    private Movie chosenMovie;

    //Bean related attributes to be linked to fields
        //Used to manually add a new movie
    private String movieName;
    private String movieGenre;
    private String movieRuntime;
    private String movieYear;
    private String moviePlot;
    private String moviePoster;



    @PersistenceContext(unitName = "Exercise1")
    @Transient private EntityManager em;

    public String addMovie() {
        // Adding a new film without adding actors or directors
        try {
            int runtime = Integer.parseInt(movieRuntime);
            int year = Integer.parseInt(movieYear);
            em.merge(new Movie(movieName, null, null, movieGenre, runtime, year, moviePlot, moviePoster));
        }catch (Exception e) {
            System.err.println("ISSUE WITH ADDING MOVIE TO DATABASE");
        }
        return movieName;
    }
}
