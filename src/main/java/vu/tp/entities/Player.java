package vu.tp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Player.findAll", query = "select a from Player as a")
})
@Table(name = "PLAYER")
@Getter @Setter
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name="JERSEY_NUMBER")
    private Integer jerseyNumber;

    @ManyToMany
    @JoinTable(name="TEAM_PLAYER",
    joinColumns = @JoinColumn(name="player_id"),
    inverseJoinColumns = @JoinColumn(name="team_id"))
    private Set<Team> teams = new HashSet<>();

    @Getter @Setter
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<PlayerGameStats> playerGameStatsList;

    public void addPlayerGameStats(PlayerGameStats playerGameStats){
        this.playerGameStatsList.add(playerGameStats);
        playerGameStats.setPlayer(this);
    }

    public Player() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
