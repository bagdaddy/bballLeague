package vu.tp.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import vu.tp.mybatis.model.Game;

@Mapper
public interface GameMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GAME
     *
     * @mbg.generated Tue Apr 21 19:43:43 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GAME
     *
     * @mbg.generated Tue Apr 21 19:43:43 EEST 2020
     */
    int insert(Game record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GAME
     *
     * @mbg.generated Tue Apr 21 19:43:43 EEST 2020
     */
    Game selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GAME
     *
     * @mbg.generated Tue Apr 21 19:43:43 EEST 2020
     */
    List<Game> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GAME
     *
     * @mbg.generated Tue Apr 21 19:43:43 EEST 2020
     */
    int updateByPrimaryKey(Game record);

    Game selectByTeam(Integer id);

    Integer getWinCount(Integer teamId);

    Integer getLossCount(Integer teamId);
}