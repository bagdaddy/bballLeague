package vu.tp.utils;

import vu.tp.entities.Game;
import vu.tp.entities.Player;
import vu.tp.entities.PlayerGameStats;
import vu.tp.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class PlayerStatsGenerator implements Generator, Serializable {

    @LoggedInvocation
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
        List<Player> homeTeamPlayers = new ArrayList<>();
        List<Player> awayTeamPlayers = new ArrayList<>();

        for(PlayerGameStats playerStatsFromGame : playerGameStatsList){
            if(playerStatsFromGame.getTeam().equals(playerStatsFromGame.getGame().getHomeTeam())){
                homeTeamPlayers.add(playerStatsFromGame.getPlayer());
            }else{
                awayTeamPlayers.add(playerStatsFromGame.getPlayer());
            }
        }

        //calculate points, rebounds
        for(PlayerGameStats playerStatsFromGame : playerGameStatsList){
            int points, rebounds;
            if(playerStatsFromGame.getGame().getHomeTeam().equals(playerStatsFromGame.getTeam())){
                if(homeTeamPlayers.size() > 1){
                    points = new Random().nextInt(homePoints - homePointsSoFar + 1);
                }else{
                    points = homePoints - homePointsSoFar;
                }
                rebounds = new Random().nextInt(15);
                homePointsSoFar += points;
                homeTeamPlayers.remove(playerStatsFromGame.getPlayer());
            }else{
                if(awayTeamPlayers.size() > 1){
                    points = new Random().nextInt(awayPoints - awayPointsSoFar + 1);
                }else{
                    points = awayPoints - awayPointsSoFar;
                }
                rebounds = new Random().nextInt(15);
                awayPointsSoFar += points;
                awayTeamPlayers.remove(playerStatsFromGame.getPlayer());
            }
            playerStatsFromGame.setPoints(points);
            System.out.println(playerStatsFromGame.getPlayer().getName() + " " + playerStatsFromGame.getPoints());
            playerStatsFromGame.setRebounds(rebounds);
        }

        //calculate assists
        for(PlayerGameStats playerGameStats : playerGameStatsList){
            int assists;
            if(playerGameStats.getGame().getHomeTeam().equals(playerGameStats.getTeam())){
                int maxAssists = (int)((homePoints - playerGameStats.getPoints()) / 2.3);
                assists = maxAssists > 0 ? new Random().nextInt(maxAssists) : maxAssists;
            }else{
                int maxAssists = (int)((awayPoints - playerGameStats.getPoints()) / 2.3);
                assists = maxAssists > 0 ? new Random().nextInt(maxAssists) : maxAssists;
            }
            playerGameStats.setAssists(assists);
        }
        game.setPlayerGameStatsList(playerGameStatsList);
        return game;
    }
}
