package vu.tp.rest;

import lombok.Getter;
import lombok.Setter;
import vu.tp.entities.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TeamDto {
    private Integer id;
    private String name;
    private Integer leagueId;
}
