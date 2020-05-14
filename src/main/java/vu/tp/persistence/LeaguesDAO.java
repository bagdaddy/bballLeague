package vu.tp.persistence;

import vu.tp.entities.League;
import vu.tp.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LeaguesDAO {

    @Inject
    private EntityManager em;

    public List<League> loadAll() {
        return em.createNamedQuery("League.findAll", League.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public League update(League team){ return em.merge(team); }

    public void persist(League team){
        this.em.persist(team);
    }

    public League findOne(Integer id) {
        return em.find(League.class, id);
    }
}
