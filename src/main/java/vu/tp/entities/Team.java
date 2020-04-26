package vu.tp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Team.findAll", query = "select t from Team as t")
})
@Table(name = "TEAM")
@Getter @Setter
public class Team {

    public Team(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name="LEAGUE_ID")
    private League league;

    @ManyToMany(mappedBy="teams")
    private Set<Player> players = new HashSet<>();

    @Getter @Setter
    @OneToMany(mappedBy = "team")
    List<PlayerGameStats> playerGameStatsList;


    @Getter @Setter
    @Column(
            columnDefinition = "integer default 0",
            nullable = false
    )
    private Integer wins;

    @Getter @Setter
    @Column(columnDefinition = "integer default 0", nullable = false)
    private Integer losses = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
