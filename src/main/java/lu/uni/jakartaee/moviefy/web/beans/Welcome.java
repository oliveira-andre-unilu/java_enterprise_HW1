package lu.uni.jakartaee.moviefy.web.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@SessionScoped
@Named("welcomeBean")
public class Welcome implements Serializable {
    //No attributes needed for the moment, it will only be used for navigation

    //Constructor
    public Welcome() {}

    //Navigation methods
    public String createNewMovie(){
        return "createNewMoviePage";
    }

    public String searchMovie(){
        return "searchMoviePage";
    }
}
