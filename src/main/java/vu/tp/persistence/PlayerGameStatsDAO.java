package vu.tp.persistence;

import vu.tp.entities.Player;
import vu.tp.entities.PlayerGameStats;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PlayerGameStatsDAO {
    @Inject
    private EntityManager em;

    public void persist(PlayerGameStats playerGameStats){
        this.em.persist(playerGameStats);
    }

    public PlayerGameStats findOne(Integer id){
        return em.find(PlayerGameStats.class, id);
    }

    public PlayerGameStats update(PlayerGameStats playerGameStats){
        return em.merge(playerGameStats);
    }
}
