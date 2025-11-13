package lu.uni.jakartaee.moviefy.service;

import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lu.uni.jakartaee.moviefy.jpa.Actor;

import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Transactional
public class ActorService implements Serializable {

    // Needed attributes

    @PersistenceContext(unitName = "Exercise1")
    @Transient private EntityManager em;

    //Constructor
    public ActorService() {}

    //Overall methods
    public Actor findActorByName(String name) {
        return em.find(Actor.class, name);
    }

    public boolean createActor(Actor actor) {
        try{
            em.persist(actor);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean updateActor(Actor actor) {
        try{
            em.merge(actor);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean deleteActor(Actor actor) {
        try{
            em.remove(em.merge(actor));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<Actor> findAllActors() {
        return em.createNamedQuery("Actor.findAll", Actor.class).getResultList();
    }
}
