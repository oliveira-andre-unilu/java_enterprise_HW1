package lu.uni.jakartaee.moviefy.service;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lu.uni.jakartaee.moviefy.jpa.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("movieService")
@SessionScoped
@Transactional
public class MovieService implements Serializable {

    //Needed attributes
    private List<Movie> movies;//Cache of movies

    @PersistenceContext(unitName = "Exercise1")
    @Transient private EntityManager em;

    //Constructor
    public MovieService() {
        movies = new ArrayList<Movie>();
    }

    //Overall methods
    public Movie addMovie(String title, String directorName, String actorNames,
                          String genre, int runTime, int year, String description, String posterLocation) {
        //Fetching information about the actors and director, not done at the moment
        Movie movie = new Movie(title, null, null, genre, runTime, year, description, posterLocation);
        try{
            em.merge(movie);
            this.movies.add(movie);
            return movie;
        }catch(Exception e){
            return null;
        }
    }

    public List<Movie> getAllMovies() {
        //Currently method will always go to the database and read all the data from there
        List<Movie> allmovies = em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
        return allmovies;
    }

}
