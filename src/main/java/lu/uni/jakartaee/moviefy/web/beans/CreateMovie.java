package lu.uni.jakartaee.moviefy.web.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lu.uni.jakartaee.moviefy.jpa.Movie;
import lu.uni.jakartaee.moviefy.service.MovieService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named("createMovie")
public class CreateMovie implements Serializable {
    //Attributes related to fields in the xhtml file
    private String movieTitle;
    private String directorName;
    private String actorNames = "";
    private String genre;
    private int runTime;
    private int year;
    private String description;
    private String posterDescription;
    private String creationStatus;
    private List<String> allActors = new ArrayList<>();

    //Business logic elements
    @Inject
    private MovieService movieService;

    //Generic Constructor
    public CreateMovie() {}

    //General actions
    public String createMovieAction(){
        //Trying to fetch a Movie with existing data
        if(!isCreationPossible()){
            String message = "All fields must be filled";
            this.creationStatus = message;
            return message;
        }
        Movie movie = null;
        try{
            movie = movieService.addMovie(this.movieTitle, this.directorName, this.allActors, this.genre, this.runTime,
                    this.year, this.description, this.posterDescription);
        }catch(Exception e){
            this.creationStatus = e.getMessage() + e.getClass().getName();
            return creationStatus;
        }

        if(movie == null){
            String message = "There was an error creating the movie";
            this.creationStatus = message;
            return message;
        }
        this.creationStatus = "CREATED NEW MOVIE: " + movie.toString();
        return "Movie created successfully";
    }

    //Helper functions
    public boolean isCreationPossible(){
        if(movieTitle.isEmpty()){
            return false;
        }
        if(directorName.isEmpty()){
            return false;
        }
        if(allActors.isEmpty()){
            return false;
        }
        if(genre.isEmpty()){
            return false;
        }
        if(runTime<=0){
            return false;
        }
        if(year<=0){
            return false;
        }
        return !description.isEmpty();
    }

    //Getters and Setters
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCreationStatus() {
        return creationStatus;
    }

    public void setCreationStatus(String creationStatus) {
        this.creationStatus = creationStatus;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setActorNames(String actorNames) {
        this.actorNames = actorNames;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosterDescription(String posterDescription) {
        this.posterDescription = posterDescription;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getActorNames() {
        return actorNames;
    }

    public String getGenre() {
        return genre;
    }

    public int getRunTime() {
        return runTime;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterDescription() {
        return posterDescription;
    }

    public List<String> getAllActors() {
        return allActors;
    }

    public void setAllActors(List<String> allActors) {
        this.allActors = allActors;
    }
}
