package vu.tp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Game.findAll", query = "select g from Game as g")
})
@Getter @Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="TEAM_HOME")
    private Team homeTeam;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="TEAM_AWAY")
    private Team awayTeam;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="LEAGUE_ID")
    private League league;

    @Getter @Setter
    private Integer homeTeamPoints;

    @Getter @Setter
    private Integer awayTeamPoints;

    @Getter @Setter
    @OneToMany(mappedBy = "game")
    private List<PlayerGameStats> playerGameStatsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game that = (Game) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (homeTeam.getName() != null && awayTeam.getName() != null ? ( awayTeam.getName() + homeTeam.getName()).hashCode() : 0);
        return result;
    }
}
