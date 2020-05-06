package vu.tp.utils;

import vu.tp.entities.Game;
import vu.tp.entities.Player;
import vu.tp.entities.PlayerGameStats;
import vu.tp.persistence.PlayersDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class PlayerStatsGenerator implements Generator, Serializable {
    @Inject
    PlayersDAO playersDAO;
    public Game generateStats(Game game){
        System.out.println("Regular stats generator");
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){

        }
        int awayPoints = game.getAwayTeamPoints();
        int awayAssistsSoFar = 0, awayPointsSoFar = 0;
        int maxAssistsAway = (int)(awayPoints / 2.3);
        int homePoints = game.getHomeTeamPoints();
        int maxAssistsHome = (int)(homePoints / 2.3);
        int homeAssistsSoFar = 0, homePointsSoFar = 0;
        List<PlayerGameStats> playerGameStatsList = game.getPlayerGameStatsList();
        for(PlayerGameStats playerStatsFromGame : playerGameStatsList){
            int points, assists, rebounds;
            if(playerStatsFromGame.getGame().getHomeTeam().equals(playerStatsFromGame.getTeam())){
                points = new Random().nextInt(homePoints - homePointsSoFar + 1);
                assists = new Random().nextInt(maxAssistsHome - homeAssistsSoFar + 1);
                rebounds = new Random().nextInt(15);
                homeAssistsSoFar += assists;
                homePointsSoFar += points;
            }else{
                points = new Random().nextInt(awayPoints - awayPointsSoFar + 1);
                assists = new Random().nextInt( maxAssistsAway - awayAssistsSoFar + 1);
                rebounds = new Random().nextInt(15);
                System.out.println(points);
                System.out.println(assists);
                System.out.println(rebounds);
                awayAssistsSoFar += assists;
                awayPointsSoFar += points;
            }
            playerStatsFromGame.setPoints(points);
            playerStatsFromGame.setRebounds(rebounds);
            playerStatsFromGame.setAssists(assists);
        }
        game.setPlayerGameStatsList(playerGameStatsList);
        return game;
    }
}
