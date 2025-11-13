package lu.uni.jakartaee.moviefy.service;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lu.uni.jakartaee.moviefy.jpa.Actor;
import lu.uni.jakartaee.moviefy.jpa.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("movieService")
@SessionScoped
@Transactional
public class MovieService implements Serializable {

    //Needed attributes

    @PersistenceContext(unitName = "Exercise1")
    @Transient private EntityManager em;

    @Inject
    ActorService actorService;

    //Constructor
    public MovieService() {

    }

    //Overall methods
    public Movie addMovie(String title, String directorName, String actorNames,
                          String genre, int runTime, int year, String description, String posterLocation) {
        //Fetching information about the actors and director, not done at the moment
            //Trying to find the actor and possibly create it
        Actor actor = actorService.findActorByName(actorNames);
        if(actor == null){
            boolean creation = actorService.createActor(new Actor(actorNames, new ArrayList<>()));
            if(!creation){
                return null;
            }
        }
            //Trying to find the director and possibly create it

            //Creating movie
        Movie movie = new Movie(title, null, null, genre, runTime, year, description, posterLocation);
        try{
            em.merge(movie);
        }catch(Exception e){
            return null;
        }

            //Relinking the actor
        actor.addMovie(movie);
        actorService.updateActor(actor);
        return movie;
    }

    public List<Movie> getAllMovies() {
        //Currently method will always go to the database and read all the data from there
        List<Movie> allmovies = new ArrayList<>();

        allmovies = em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
        if(allmovies == null){
            return new ArrayList<>();
        }else{
            return allmovies;
        }
    }

}
