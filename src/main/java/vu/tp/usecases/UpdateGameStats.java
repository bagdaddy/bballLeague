package vu.tp.usecases;


import lombok.Getter;
import lombok.Setter;
import vu.tp.entities.Game;
import vu.tp.entities.PlayerGameStats;
import vu.tp.entities.Team;
import vu.tp.interceptors.LoggedInvocation;
import vu.tp.persistence.GamesDAO;
import vu.tp.persistence.PlayerGameStatsDAO;
import vu.tp.persistence.PlayersDAO;
import vu.tp.entities.Player;
import vu.tp.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Getter @Setter
@Named
public class UpdateGameStats implements Serializable{

    @Inject
    private PlayersDAO playersDAO;

    @Inject
    private TeamsDAO teamsDAO;

    @Inject
    private PlayerGameStatsDAO playerGameStatsDAO;

    @Getter @Setter
    private List<PlayerGameStats> playerGameStatsList;

    @Inject
    private GamesDAO gamesDAO;

    @Getter
    private Team awayTeam;

    @Getter
    private Team homeTeam;

    @Getter @Setter
    private Game game;

    private PlayerGameStats persistBlankPlayerGameStats(Player player, Team team){
        PlayerGameStats playerGameStatsToCreate = new PlayerGameStats();
        playerGameStatsToCreate.setGame(this.game);
        player.addPlayerGameStats(playerGameStatsToCreate);
        playerGameStatsToCreate.setTeam(team);
        playerGameStatsToCreate.setPoints(0);
        playerGameStatsToCreate.setAssists(0);
        playerGameStatsToCreate.setRebounds(0);
        return playerGameStatsToCreate;
    }

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer gameId = Integer.parseInt(requestParameters.get("gameId"));
        this.game = gamesDAO.findOne(gameId);

        awayTeam = game.getAwayTeam();
        homeTeam = game.getHomeTeam();

        playerGameStatsList = game.getPlayerGameStatsList();
        if(playerGameStatsList.size() == 0){
            for(Player player : awayTeam.getPlayers()){
                System.out.println("Saving player stats for away team " + player.getName());
                playerGameStatsList.add(persistBlankPlayerGameStats(player, awayTeam));
            }
            for(Player player : homeTeam.getPlayers()){
                System.out.println("Saving player stats for home team " + player.getName());
                playerGameStatsList.add(persistBlankPlayerGameStats(player, homeTeam));
            }
            game.setPlayerGameStatsList(playerGameStatsList);
        }
        System.out.println("New statlines: " + playerGameStatsList.size());
    }

    @Transactional
    public String saveStats(){
        System.out.println("Saving records");
        for(PlayerGameStats playerGameStats : this.playerGameStatsList){
            if(playerGameStats.getId() != null){
                playerGameStatsDAO.update(playerGameStats);
                System.out.println("updating");
            }else{
                playerGameStatsDAO.persist(playerGameStats);
                System.out.println("saving new record");
            }
        }
        return "/gameDetails?faces-redirect=true&gameId=" + this.game.getId();
    }

}
