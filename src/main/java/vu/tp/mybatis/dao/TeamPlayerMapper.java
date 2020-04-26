package vu.tp.mybatis.dao;

import org.mybatis.cdi.Mapper;
import vu.tp.mybatis.model.Player;
import vu.tp.mybatis.model.Team;

import java.util.List;
import java.util.Set;

@Mapper
public interface TeamPlayerMapper {
    int deleteTeamPlayer(Integer playerId, Integer teamId);

    int addTeamPlayer(Integer playerId, Integer teamId);

    Set<Player> selectPlayers(Integer teamId);

    Set<Team> selectTeams(Integer playerId);

}
