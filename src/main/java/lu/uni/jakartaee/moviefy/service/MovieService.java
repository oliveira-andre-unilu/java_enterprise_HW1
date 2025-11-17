package lu.uni.jakartaee.moviefy.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lu.uni.jakartaee.moviefy.exeptions.ActorNotCreatedException;
import lu.uni.jakartaee.moviefy.exeptions.DirectorNotCreatedException;
import lu.uni.jakartaee.moviefy.jpa.Actor;
import lu.uni.jakartaee.moviefy.jpa.Director;
import lu.uni.jakartaee.moviefy.jpa.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("movieService")
@Stateless
public class MovieService implements Serializable {

    //Needed attributes

    @PersistenceContext(unitName = "Exercise1")
    private EntityManager emMovie;

    @Inject
    ActorService actorService;
    @Inject
    DirectorService directorService;

    //Constructor
    public MovieService() {

    }

    //Overall methods
    public Movie addMovie(String title, String directorName, List<String> actorNames,
                          String genre, int runTime, int year, String description, String posterLocation) {
        Director director;
        List<Actor> actors;
        try {
            director = findAndCreateDirector(directorName);
            actors = findAndCreateActor(actorNames);
        } catch (DirectorNotCreatedException | ActorNotCreatedException e) {
            return null;
        }


        // Create movie
        Movie movie = new Movie(title, director, actors, genre, runTime, year, description, posterLocation);

        // Persist movie (cascades to director & actors)
        emMovie.persist(movie);

        return movie;
    }

    public List<Movie> getAllMovies() {
        //Currently method will always go to the database and read all the data from there
        List<Movie> allmovies = new ArrayList<>();

        allmovies = emMovie.createNamedQuery("Movie.findAll", Movie.class).getResultList();
        if(allmovies == null){
            return new ArrayList<>();
        }else{
            return allmovies;
        }
    }

    //Helper functions
    private List<Actor> findAndCreateActor(List<String> actorNames) throws ActorNotCreatedException {
        List<Actor> actors = new ArrayList<>();
        for (String actorName : actorNames) {
            Actor tempActor = actorService.findActorByName(actorName);
            if(tempActor == null){
                tempActor = new Actor(actorName, new ArrayList<>());
                boolean creation = actorService.createActor(tempActor);
                if(!creation){
                    throw new ActorNotCreatedException("Could not create actor: " + actorName);
                }
                actors.add(tempActor);
            }
        }
        return actors;
    }

    private Director findAndCreateDirector(String directorName) throws DirectorNotCreatedException {
        Director tempDirector = directorService.findDirectorByName(directorName);
        if(tempDirector == null){
            tempDirector = new Director(directorName, 2000);
            boolean result = directorService.createDirector(tempDirector);
            if(!result){
                throw new DirectorNotCreatedException("Could not create director: " + directorName);
            }
        }
        return tempDirector;
    }

    private boolean relinkAllActorsToMovie(List<Actor> actors, Movie movie) {
        for(Actor actor : actors){
            boolean added = actor.addMovie(movie);
            if(!added){return false;}
            actorService.updateActor(actor);
        }
        return true;
    }

    private boolean relinkDirectorToMovie(Director director, Movie movie) {
        boolean added = director.addMovie(movie);
        if(!added){return false;}
        directorService.updateDirector(director);
        return true;
    }

}
