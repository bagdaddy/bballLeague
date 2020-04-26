package vu.tp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NamedQueries({
        @NamedQuery(name = "PlayerGameStats.findAll", query = "select a from PlayerGameStats as a")
})
@Getter @Setter
public class PlayerGameStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="PLAYER_ID")
    private Player player;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="GAME_ID")
    private Game game;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    @Getter @Setter
    private Integer points;

    @Getter @Setter
    private Integer rebounds;

    @Getter @Setter
    private Integer assists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerGameStats that = (PlayerGameStats) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (player.getName() != null ? player.getName().hashCode() : 0);
        return result;
    }
}

