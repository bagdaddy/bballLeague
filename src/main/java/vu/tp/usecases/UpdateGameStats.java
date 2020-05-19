package vu.tp.usecases;


import lombok.Getter;
import lombok.Setter;
import vu.tp.entities.Game;
import vu.tp.entities.PlayerGameStats;
import vu.tp.entities.Team;
import vu.tp.interceptors.CustomInterceptor;
import vu.tp.persistence.GamesDAO;
import vu.tp.persistence.PlayerGameStatsDAO;
import vu.tp.persistence.PlayersDAO;
import vu.tp.entities.Player;
import vu.tp.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Getter @Setter
@Named
public class UpdateGameStats implements Serializable{

    @Inject
    private PlayersDAO playersDAO;

    @Inject
    private GeneratePlayerStats generatePlayerStats;

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
        try{
            if(generatePlayerStats.getCurrentGame() != null && generatePlayerStats.getCurrentGame().equals(this.game)){
                playerGameStatsList = generatePlayerStats.getGeneratedStats();

            }
        }catch(Exception e){
            playerGameStatsList = null;
        }

        if(playerGameStatsList == null ){
            playerGameStatsList = game.getPlayerGameStatsList();
        }

        if(playerGameStatsList.size() == 0){
            for(Player player : awayTeam.getPlayers()){
                playerGameStatsList.add(persistBlankPlayerGameStats(player, awayTeam));
            }
            for(Player player : homeTeam.getPlayers()){
                playerGameStatsList.add(persistBlankPlayerGameStats(player, homeTeam));
            }
            game.setPlayerGameStatsList(playerGameStatsList);
        }
    }

    @Transactional
    @CustomInterceptor
    public String saveStats(){
        generatePlayerStats.clearGeneratedStats(false);
        for(PlayerGameStats playerGameStats : this.playerGameStatsList){
            if(playerGameStats.getId() != null){
                try{
                    playerGameStatsDAO.update(playerGameStats);
                }catch(OptimisticLockException e){
                    return "/gameDetails?faces-redirect=true&gameId=" + this.game.getId() + "&error=optimistic-lock-exception";
                }
            }else{
                playerGameStatsDAO.persist(playerGameStats);
            }
        }
        return "/gameDetails?faces-redirect=true&gameId=" + this.game.getId();
    }

}
