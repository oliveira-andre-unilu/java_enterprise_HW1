package lu.uni.jakartaee.moviefy.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lu.uni.jakartaee.moviefy.jpa.Actor;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ActorService implements Serializable {

    @PersistenceContext(unitName = "Exercise1")
    private EntityManager emActor;

    public Actor findActorByName(String name) {
        List<Actor> result = emActor.createQuery(
                        "SELECT a FROM main_actor a WHERE a.name = :name", Actor.class)
                .setParameter("name", name)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public boolean createActor(Actor actor) {
        try {
            emActor.persist(actor);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateActor(Actor actor) {
        try {
            emActor.merge(actor);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteActor(Actor actor) {
        try {
            emActor.remove(emActor.merge(actor));
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Actor> findAllActors() {
        return emActor.createNamedQuery("Actor.findAll", Actor.class).getResultList();
    }
}
