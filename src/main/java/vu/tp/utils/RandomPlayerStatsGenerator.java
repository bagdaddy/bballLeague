package vu.tp.utils;

import vu.tp.entities.Game;
import vu.tp.entities.PlayerGameStats;
import vu.tp.mybatis.model.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
@Alternative
public class RandomPlayerStatsGenerator implements Serializable {
    public Game generateStats(Game game){
        System.out.println("Random stats generator");
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){

        }
        List<PlayerGameStats> playerGameStatsList = game.getPlayerGameStatsList();
        for(PlayerGameStats playerGameStats : playerGameStatsList){
            playerGameStats.setPoints(1);
            playerGameStats.setRebounds(1);
            playerGameStats.setAssists(1);
        }
        game.setPlayerGameStatsList(playerGameStatsList);
        return game;
    }

}
