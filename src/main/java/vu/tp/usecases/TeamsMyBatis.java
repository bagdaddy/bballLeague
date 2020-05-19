package vu.tp.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.tp.entities.PlayerGameStats;
import vu.tp.interceptors.CustomInterceptor;
import vu.tp.mybatis.dao.*;
import vu.tp.mybatis.model.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

@Model
public class TeamsMyBatis {

    @Inject
    private GameMapper gameMapper;

    @Inject
    private TeamMapper teamMapper;

    @Inject
    private LeagueMapper leagueMapper;

    @Inject
    private TeamPlayerMapper teamPlayerMapper;

    @Inject
    private PlayergamestatsMapper playergamestatsMapper;

    @Getter
    private List<Team> allTeams;

    private Playergamestats pgsToCreate = new Playergamestats();

    @Getter @Setter
    private League league;

    @Getter @Setter
    private Team teamToCreate = new Team();

    @Getter @Setter
    private Game gameToAdd = new Game();

    @Getter @Setter
    private Integer homeTeamId;

    @Getter @Setter
    private Integer awayTeamId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer leagueId = Integer.valueOf(requestParameters.get("leagueId"));
        this.league = leagueMapper.selectByPrimaryKey(leagueId);
        loadLeagueTeams();
    }

    private void loadAllTeams() {
        this.allTeams = teamMapper.selectAll();
    }

    @CustomInterceptor
    private void loadLeagueTeams(){
        this.allTeams = teamMapper.selectTeamsByLeagueId(league.getId());
    }

    @Transactional
    public String createTeam() {
        teamToCreate.setLeagueId(this.league.getId());
        teamToCreate.setWins(0);
        teamToCreate.setLosses(0);
        teamMapper.insert(teamToCreate);
        return "/teams?faces-redirect=true&leagueId=" + this.league.getId();
    }

    @Transactional
    public String addGame(){
        if(gameToAdd.getTeamHome().equals(gameToAdd.getTeamAway())){
            return "/teams?faces-redirect=true&leagueId=" + this.league.getId() + "&error=same-team-exception";
        }
        Team homeTeam = teamMapper.selectByPrimaryKey(gameToAdd.getTeamHome());
        Team awayTeam = teamMapper.selectByPrimaryKey(gameToAdd.getTeamAway());
        if(gameToAdd.getHometeampoints() > gameToAdd.getAwayteampoints()){
            homeTeam.setWins(homeTeam.getWins() + 1);
            awayTeam.setLosses(awayTeam.getLosses() + 1);
        }else if(gameToAdd.getAwayteampoints() > gameToAdd.getHometeampoints()){
            homeTeam.setLosses(homeTeam.getLosses() + 1);
            awayTeam.setWins(awayTeam.getWins() + 1);
        }
        teamMapper.updateByPrimaryKey(awayTeam);
        teamMapper.updateByPrimaryKey(homeTeam);
        gameToAdd.setLeagueId(this.league.getId());
        gameMapper.insert(gameToAdd);
        return "/gameDetails?faces-redirect=true&gameId=" + this.gameToAdd.getId();
    }
}
