package vu.tp.mybatis.model;

public class Player {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PLAYER.ID
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PLAYER.JERSEY_NUMBER
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    private Integer jerseyNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PLAYER.NAME
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PLAYER.TEAM_ID
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    private Integer teamId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PLAYER.ID
     *
     * @return the value of PUBLIC.PLAYER.ID
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PLAYER.ID
     *
     * @param id the value for PUBLIC.PLAYER.ID
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PLAYER.JERSEY_NUMBER
     *
     * @return the value of PUBLIC.PLAYER.JERSEY_NUMBER
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PLAYER.JERSEY_NUMBER
     *
     * @param jerseyNumber the value for PUBLIC.PLAYER.JERSEY_NUMBER
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PLAYER.NAME
     *
     * @return the value of PUBLIC.PLAYER.NAME
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PLAYER.NAME
     *
     * @param name the value for PUBLIC.PLAYER.NAME
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PLAYER.TEAM_ID
     *
     * @return the value of PUBLIC.PLAYER.TEAM_ID
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PLAYER.TEAM_ID
     *
     * @param teamId the value for PUBLIC.PLAYER.TEAM_ID
     *
     * @mbg.generated Wed Apr 15 22:05:38 EEST 2020
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}