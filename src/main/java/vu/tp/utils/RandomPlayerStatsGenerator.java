package vu.tp.utils;

import vu.tp.entities.Game;
import vu.tp.entities.PlayerGameStats;
import vu.tp.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@Alternative
public class RandomPlayerStatsGenerator implements Generator, Serializable {

    @LoggedInvocation
    public Game generateStats(Game game){
        System.out.println("Random stats generator");
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){

        }
        List<PlayerGameStats> playerGameStatsList = game.getPlayerGameStatsList();
        for(PlayerGameStats playerGameStats : playerGameStatsList){
            int pts = 0;
            int ast = 0;
            int reb = 0;
            int gameCount = playerGameStats.getPlayer().getPlayerGameStatsList().size();
            for(PlayerGameStats playerGameStats1 : playerGameStats.getPlayer().getPlayerGameStatsList()){
                pts += playerGameStats1.getPoints();
                ast += playerGameStats1.getAssists();
                reb += playerGameStats1.getRebounds();
            }
            playerGameStats.setPoints(new Random().nextInt((int)(pts / gameCount) + 10) + 10);
            playerGameStats.setRebounds(new Random().nextInt((int)(reb / gameCount) + 4) + 3);
            playerGameStats.setAssists(new Random().nextInt((int)(ast / gameCount) + 3) + 2);
        }
        game.setPlayerGameStatsList(playerGameStatsList);
        return game;
    }

}
