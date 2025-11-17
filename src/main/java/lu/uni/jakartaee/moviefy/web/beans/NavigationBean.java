package lu.uni.jakartaee.moviefy.web.beans;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;

@Stateless
@Named("navigationBean")
public class NavigationBean {

    public String goToHomePage(){
        return "HOME";
    }

    public String goToCreateMoviePage(){
        return "CREATE MOVIE";
    }

    public String goToMovieListPage(){
        return "MOVIE LIST";
    }
}
