package vu.tp.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import vu.tp.mybatis.model.League;

@Mapper
public interface LeagueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LEAGUE
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LEAGUE
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    int insert(League record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LEAGUE
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    League selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LEAGUE
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    List<League> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LEAGUE
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    int updateByPrimaryKey(League record);
}