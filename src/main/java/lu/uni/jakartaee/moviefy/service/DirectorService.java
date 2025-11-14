package lu.uni.jakartaee.moviefy.service;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lu.uni.jakartaee.moviefy.jpa.Director;

import java.io.Serializable;
import java.util.List;

@Stateless
public class DirectorService implements Serializable {

    //Needed attributes
    @PersistenceContext(unitName = "Exercise1")
    private EntityManager emDirector;

    //Constructor
    public DirectorService() {}

    //General methods
    public Director findDirectorByName(String name) {
        try {
            return emDirector.createQuery(
                            "SELECT d FROM director d WHERE d.name = :name", Director.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Director> findAllDirectors() {
        return emDirector.createNamedQuery("Director.findAll", Director.class).getResultList();
    }

    public boolean createDirector(Director director) {
        try{
            emDirector.persist(director);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean updateDirector(Director director) {
        try{
            emDirector.merge(director);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean deleteDirector(Director director) {
        try{
            emDirector.remove(director);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
