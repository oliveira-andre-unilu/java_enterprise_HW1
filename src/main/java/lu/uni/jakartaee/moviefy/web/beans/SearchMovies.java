package lu.uni.jakartaee.moviefy.web.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lu.uni.jakartaee.moviefy.jpa.Movie;
import lu.uni.jakartaee.moviefy.service.MovieService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named("searchMovie")
public class SearchMovies implements Serializable {
    //Attributes related to fields in the xhtml file

    //Attributes related to the filtering
    private Map<String, String> filtersAvailable;
    private String filterBy;
    private String searchTerm;
    private List<String> appliedFilters = new ArrayList<>();

    //Attributes related to the table
    private List<Movie> loadedMovies;
    private Movie selectedMovie;

    //Dependencies
    @Inject
    private MovieService movieService;


    //Constructor
    public SearchMovies() {
        filtersAvailable = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        //Adding filter options
        filtersAvailable = new HashMap<>();
        filtersAvailable.put("By Year", "YEAR");
        filtersAvailable.put("By Title", "TITLE");
        filtersAvailable.put("By Genre", "GENRE");
        filtersAvailable.put("By Director Name", "DIRECTOR");

        //Loading data table
        loadedMovies = new ArrayList<>();
        loadedMovies = movieService.getAllMovies();
        // loadedMovies.add(new Movie("Some film", null, null, "Comedy", 65, 2000, "TESTING", "https:blabla"));
    }

    public String applyChange(){

        List<Movie> filteredSet = new ArrayList<>();
        switch (filterBy) {
            case "YEAR" -> {
                for (Movie movie : loadedMovies) {
                    if (String.valueOf(movie.getYear()).contains(this.searchTerm)) {
                        filteredSet.add(movie);
                    }
                }
                this.appliedFilters.add("Filter by year: " + this.searchTerm);
            }
            case "TITLE" -> {
                for (Movie movie : loadedMovies) {
                    if (String.valueOf(movie.getTitle()).contains(this.searchTerm)) {
                        filteredSet.add(movie);
                    }
                }
                this.appliedFilters.add("Filter by title: " + this.searchTerm);
            }
            case "GENRE" -> {
                for (Movie movie : loadedMovies) {
                    if (String.valueOf(movie.getGenre()).contains(this.searchTerm)) {
                        filteredSet.add(movie);
                    }
                }
                this.appliedFilters.add("Filter by genre: " + this.searchTerm);
            }
            case "DIRECTOR" -> {
                for (Movie movie : loadedMovies) {
                    if (String.valueOf(movie.getDirector().getName()).contains(this.searchTerm)) {
                        filteredSet.add(movie);
                    }
                }
                this.appliedFilters.add("Filter by director: " + this.searchTerm);
            }
        }
        setLoadedMovies(filteredSet);
        return "Filtering successfully";
    }

    public String resetFiltering(){
        this.appliedFilters.clear();
        setLoadedMovies(movieService.getAllMovies());
        return "Reset successfully";
    }

    // Getters and setters

    public void setFiltersAvailable(Map<String, String> filtersAvailable) {
        this.filtersAvailable = filtersAvailable;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public Map<String, String> getFiltersAvailable() {
        return filtersAvailable;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Movie> getLoadedMovies() {
        return loadedMovies;
    }

    public void setLoadedMovies(List<Movie> loadedMovies) {
        this.loadedMovies = loadedMovies;
    }

    public List<String> getAppliedFilters() {
        return appliedFilters;
    }

    public void setAppliedFilters(List<String> appliedFilters) {
        this.appliedFilters = appliedFilters;
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }
}
