package lu.uni.jakartaee.moviefy.web.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SessionScoped
@Named("searchMovie")
public class SearchMovies implements Serializable {
    //Attributes related to fields in the xhtml file
    private Map<String, String> filtersAvailable;
    private String filterBy;

    @PostConstruct
    public void init() {
        filtersAvailable = new HashMap<>();
        filtersAvailable.put("By Year", "By Year");
    }

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
}
