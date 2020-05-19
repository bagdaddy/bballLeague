package vu.tp.rest;

import vu.tp.entities.Game;
import vu.tp.entities.Team;
import vu.tp.persistence.GamesDAO;
import vu.tp.persistence.LeaguesDAO;
import vu.tp.persistence.TeamsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@ApplicationPath("/api")
@Path("/team")
public class RestService extends Application {
    @Inject
    TeamsDAO teamsDAO;

    @Inject
    LeaguesDAO leaguesDAO;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") final Integer id){
        System.out.println("called team/" + id);
        Team team = teamsDAO.findOne(id);
        if (team == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        TeamDto teamDto = new TeamDto();
        teamDto.setId(id);
        teamDto.setName(team.getName());
        return Response.ok(teamDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer teamId, TeamDto teamData){
        Team existingTeam = teamsDAO.findOne(teamId);
        if(existingTeam == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingTeam.setName(teamData.getName());
        System.out.println(existingTeam.getName());
        teamsDAO.update(existingTeam);
        return Response.ok().build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert(TeamDto teamData){
        Team teamToCreate = new Team();
        teamToCreate.setName(teamData.getName());
        teamToCreate.setLeague(leaguesDAO.findOne(teamData.getLeagueId()));
        teamToCreate.setWins(0);
        teamToCreate.setLosses(0);
        teamsDAO.persist(teamToCreate);
        return Response.ok().build();
    }
}
