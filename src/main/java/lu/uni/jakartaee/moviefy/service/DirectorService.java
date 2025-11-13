package lu.uni.jakartaee.moviefy.service;

import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lu.uni.jakartaee.moviefy.jpa.Director;

import java.io.Serializable;
import java.util.List;

@SessionScoped
@Transactional
public class DirectorService implements Serializable {

    //Needed attributes
    @PersistenceContext(unitName = "Exercise1")
    @Transient
    private EntityManager em;

    //Constructor
    public DirectorService() {}

    //General methods
    public Director findDirectorByName(String name) {
        return em.find(Director.class, name);
    }

    public List<Director> findAllDirectors() {
        return em.createNamedQuery("Director.findAll", Director.class).getResultList();
    }

    public boolean createDirector(Director director) {
        try{
            em.persist(director);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean updateDirector(Director director) {
        try{
            em.merge(director);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //TODO:continue here
}
