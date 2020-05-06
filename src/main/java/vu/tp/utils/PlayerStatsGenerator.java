package vu.tp.utils;

import vu.tp.entities.Game;
import vu.tp.entities.PlayerGameStats;
import vu.tp.mybatis.model.Player;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class PlayerStatsGenerator implements Serializable {
    public Game generateStats(Game game){
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){

        }
        List<PlayerGameStats> playerGameStatsList = game.getPlayerGameStatsList();
        for(PlayerGameStats playerGameStats : playerGameStatsList){
            playerGameStats.setPoints(0);
            playerGameStats.setRebounds(0);
            playerGameStats.setAssists(0);
        }
        game.setPlayerGameStatsList(playerGameStatsList);
        return game;
    }

}
