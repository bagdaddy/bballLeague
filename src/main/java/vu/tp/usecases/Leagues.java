package vu.tp.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.tp.interceptors.CustomInterceptor;
import vu.tp.mybatis.dao.LeagueMapper;
import vu.tp.mybatis.dao.PlayerMapper;
import vu.tp.mybatis.model.League;
import vu.tp.mybatis.model.Player;
import vu.tp.utils.JerseyNumberGenerator;
import vu.tp.utils.NumberGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Leagues {
    @Inject
    private NumberGenerator jerseyNumberGenerator;

    @Inject
    private LeagueMapper leagueMapper;

    @Inject
    PlayerMapper playerMapper;

    @Getter
    private List<League> allLeagues;

    @Getter
    private List<Player> allPlayers;

    @Getter @Setter
    private League leagueToCreate = new League();

    @Getter @Setter
    private Player playerToCreate = new Player();

    @PostConstruct
    public void init() {
        this.loadAllLeagues();
        this.loadAllPlayers();
    }

    private void loadAllLeagues() {
        this.allLeagues = leagueMapper.selectAll();
    }
    private void loadAllPlayers() {this.allPlayers = playerMapper.selectAll();}

    @Transactional
    @CustomInterceptor
    public String createLeague() {
        leagueMapper.insert(leagueToCreate);
        return "/index?faces-redirect=true";
    }

    @Transactional
    public String createPlayer(){
        playerToCreate.setJerseyNumber(jerseyNumberGenerator.generateJerseyNumber());
        playerMapper.insert(playerToCreate);
        return "/index?faces-redirect=true";
    }
}
