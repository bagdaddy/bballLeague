package vu.tp.mybatis.dao;

import java.util.List;
import java.util.Set;

import org.mybatis.cdi.Mapper;
import vu.tp.mybatis.model.Player;

@Mapper
public interface PlayerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    int insert(Player record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    Player selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    List<Player> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYER
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    int updateByPrimaryKey(Player record);
}