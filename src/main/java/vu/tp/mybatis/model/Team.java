package vu.tp.mybatis.model;

public class Team {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TEAM.ID
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TEAM.LOSSES
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    private Integer losses;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TEAM.NAME
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TEAM.WINS
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    private Integer wins;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TEAM.LEAGUE_ID
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    private Integer leagueId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TEAM.ID
     *
     * @return the value of PUBLIC.TEAM.ID
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TEAM.ID
     *
     * @param id the value for PUBLIC.TEAM.ID
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TEAM.LOSSES
     *
     * @return the value of PUBLIC.TEAM.LOSSES
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public Integer getLosses() {
        return losses;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TEAM.LOSSES
     *
     * @param losses the value for PUBLIC.TEAM.LOSSES
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TEAM.NAME
     *
     * @return the value of PUBLIC.TEAM.NAME
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TEAM.NAME
     *
     * @param name the value for PUBLIC.TEAM.NAME
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TEAM.WINS
     *
     * @return the value of PUBLIC.TEAM.WINS
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public Integer getWins() {
        return wins;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TEAM.WINS
     *
     * @param wins the value for PUBLIC.TEAM.WINS
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public void setWins(Integer wins) {
        this.wins = wins;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TEAM.LEAGUE_ID
     *
     * @return the value of PUBLIC.TEAM.LEAGUE_ID
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public Integer getLeagueId() {
        return leagueId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TEAM.LEAGUE_ID
     *
     * @param leagueId the value for PUBLIC.TEAM.LEAGUE_ID
     *
     * @mbg.generated Tue Apr 21 21:18:39 EEST 2020
     */
    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }
}