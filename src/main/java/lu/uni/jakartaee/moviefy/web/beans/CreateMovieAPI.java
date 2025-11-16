package lu.uni.jakartaee.moviefy.web.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lu.uni.jakartaee.moviefy.api.MovieDTO;
import lu.uni.jakartaee.moviefy.exeptions.APICallNotSuccessful;
import lu.uni.jakartaee.moviefy.jpa.Movie;
import lu.uni.jakartaee.moviefy.service.MovieAPIService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SessionScoped
@Named("createMovieAPI")
public class CreateMovieAPI implements Serializable {

    //Attributes related to fields in the xhtml file
    private String movieTitle;
    private String movieType;
    private int releaseDate;
    private Map<String, String> movieTypes = new HashMap<>();
    private boolean success;
    private String creationStatus;
    private MovieDTO tempMovie;
    private boolean openDialog;

    @Inject
    MovieAPIService movieAPIService;


    @PostConstruct
    public void init() {
        movieTypes = new HashMap<>();
        movieTypes.put("movie", "movie");
        movieTypes.put("series", "series");
        movieTypes.put("episode", "episode");
    }

    //Action methods
    public String applyChange(){
        String type = movieType.equals("Any") ? null : movieType;
        Integer finalReleaseDate = releaseDate==0 ? null : releaseDate;

        try {
            tempMovie = movieAPIService.callAPI(this.movieTitle, Optional.ofNullable(type), Optional.ofNullable(finalReleaseDate));
        }catch (APICallNotSuccessful e){
            success = false;
            creationStatus = e.getMessage();
            openDialog = true;
            return "FAIL";
        }
        if(tempMovie == null){
            success = false;
            creationStatus = "Could not call the API successfully";
            openDialog = true;
            return "FAIL";
        }else if(tempMovie.getTitle() == null) {
            success = false;
            creationStatus = "Sorry, we could not find the movie";
            openDialog = true;
            return "UNKNOWN";
        } else {
            success = true;
            creationStatus = "Success, would you like to save the film " + tempMovie.getTitle() + "to your library?";
            openDialog = true;
            return "SUCCESS";
        }
    }

    public String saveTempMovie() {
        if(tempMovie == null){
            return "FAIL";
        }
        Movie result = movieAPIService.saveMovietoDB(tempMovie);
        if(result == null){
            tempMovie = null;
            success = false;
            return "FAIL";
        }
        tempMovie = null;
        success = true;
        return "SUCCESS";
    }

    //Getters and setters

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Map<String, String> getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(Map<String, String> movieTypes) {
        this.movieTypes = movieTypes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCreationStatus() {
        return creationStatus;
    }

    public void setCreationStatus(String creationStatus) {
        this.creationStatus = creationStatus;
    }

    public boolean isOpenDialog() {
        return openDialog;
    }

    public void setOpenDialog(boolean openDialog) {
        this.openDialog = openDialog;
    }
}
