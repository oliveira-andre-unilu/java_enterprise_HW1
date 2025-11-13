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
    private Map<String, String> filtersAvailable;
    private String filterBy;
    private String searchTerm;

    //Attributes related to the table
    private List<Movie> loadedMovies;
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
        filtersAvailable.put("By Year", "By Year");

        //Loading data table
        loadedMovies = new ArrayList<>();
        loadedMovies = movieService.getAllMovies();
        loadedMovies.add(new Movie("Some film", null, null, "Comedy", 65, 2000, "TESTING", "https:blabla"));
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
}
