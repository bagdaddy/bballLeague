package vu.tp.utils;

import vu.tp.mybatis.dao.LeagueMapper;
import vu.tp.mybatis.dao.PlayerMapper;
import vu.tp.mybatis.model.Player;
import vu.tp.mybatis.model.Team;
import vu.tp.persistence.PlayersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@Specializes
public class SpecialJerseyNumberGenerator extends JerseyNumberGenerator {
    @Inject
    PlayerMapper playerMapper;

    @PostConstruct
    public void init(){
        System.out.println("Special generator created");
    }

    @Override
    public Integer generateJerseyNumber(){
        System.out.println("Special generator");
        List<Player> players = playerMapper.selectAll();
        Integer num = new Random().nextInt(1000);

        for(Player player : players){
            if(num.equals(player.getJerseyNumber())){
                return generateJerseyNumber();
            }
        }
        return num;
    }
}
