package vu.tp.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.tp.entities.Player;
import vu.tp.entities.PlayerGameStats;
import vu.tp.entities.Team;
import vu.tp.persistence.PlayerGameStatsDAO;
import vu.tp.persistence.PlayersDAO;
import vu.tp.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Model
public class PlayerProfile implements Serializable {

   @Inject
   private PlayersDAO playersDAO;

   @Inject
   private PlayerGameStatsDAO playerGameStatsDAO;

   @Inject
   private TeamsDAO teamsDAO;

    @Getter
    private Set<Team> playersTeams;

    @Getter
    private List<PlayerGameStats> playerGameStatsList;

    @Getter
    @Setter
    private Player player;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("playerId"));
        player = playersDAO.findOne(playerId);
        this.playersTeams = player.getTeams();
        this.playerGameStatsList = player.getPlayerGameStatsList();
    }

}
