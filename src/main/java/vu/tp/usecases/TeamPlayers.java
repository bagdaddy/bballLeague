package vu.tp.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import vu.tp.interceptors.LoggedInvocation;
import vu.tp.mybatis.dao.PlayerMapper;
import vu.tp.mybatis.dao.TeamMapper;
import vu.tp.mybatis.dao.TeamPlayerMapper;
import vu.tp.mybatis.model.Player;
import vu.tp.mybatis.model.Team;

@Model
public class TeamPlayers implements Serializable {

    @Getter
    private String errorMessage;

    @Inject
    private TeamMapper teamMapper;

    @Inject
    private PlayerMapper playerMapper;

    @Inject
    private TeamPlayerMapper teamPlayerMapper;

    @Getter
    private Player playerToAdd = new Player();

    @Getter @Setter
    private String chosenPlayerId;

    @Getter
    private List<Player> allPlayers;

    @Getter
    private Set<Player> teamPlayers;
    @Getter
    private Team team;
    @PostConstruct
    public void init() {
        System.out.println("INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("teamId"));
        this.team = teamMapper.selectByPrimaryKey(teamId);
        loadTeamPlayers();
        loadAllPlayers();
        try{
            Integer errorCode = Integer.parseInt(requestParameters.get("error"));
            if(errorCode == 1){
                errorMessage = "Couldn't add the player to the team. Maybe the player is already on the team?";
            }
        }catch(NumberFormatException e){

        }
    }

    private void loadTeamPlayers(){ this.teamPlayers = teamPlayerMapper.selectPlayers(this.team.getId()); }

    private void loadAllPlayers(){
        this.allPlayers = playerMapper.selectAll();
        int i = 0;
        boolean removed = false;
        while(i < allPlayers.size()){
            for(Player teamPlayer : teamPlayers){
                if(allPlayers.get(i).getId().equals(teamPlayer.getId())){
                    allPlayers.remove(i);
                    removed = true;
                    break;
                }
            }
            if(removed){
                removed = false;
                i--;
                i = Integer.max(i, 0);
            }else{
                i++;
            }

        }
    }

    @Transactional
    @LoggedInvocation
    public String addPlayer() {
        System.out.println("add player");
        try{
            Integer playerId = Integer.parseInt(chosenPlayerId);
            teamPlayerMapper.addTeamPlayer(playerId, this.team.getId());
            return "players?faces-redirect=true&teamId=" + team.getId();
        }catch(PersistenceException e){
            return "players?faces-redirect=true&teamId="+team.getId() + "&error=1";
        }
    }
}
