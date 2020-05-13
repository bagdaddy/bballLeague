package vu.tp.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.tp.entities.Game;
import vu.tp.entities.PlayerGameStats;
import vu.tp.utils.Generator;
import vu.tp.utils.RandomPlayerStatsGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GeneratePlayerStats implements Serializable {
    @Inject
    Generator generator;

    private CompletableFuture<Game> statsGenerationTask = null;

    private Game gameStatsAreGeneratedFor;

    @Getter @Setter
    private String outputText = "";

    public String generatePlayerStats(Game game){
        outputText = "Stats are being generated. Please wait";
        gameStatsAreGeneratedFor = game;
        statsGenerationTask = CompletableFuture.supplyAsync(() -> generator.generateStats(game));
        statsGenerationTask.thenAccept(this::onGenerationCompleted);
        return "/gameDetails?faces-redirect=true&gameId=" + game.getId();
    }

    private void onGenerationCompleted(Game game) {
        outputText = "";
        System.out.println("generation completed");
    }

    public boolean generatedStatsExist(Game gameDisplayed){
        return gameDisplayed.equals(gameStatsAreGeneratedFor) && statsGenerationTask.isDone();
    }

    public Game getCurrentGame(){
        return this.gameStatsAreGeneratedFor;
    }

    public String clearGeneratedStats(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int gameId = Integer.parseInt(requestParameters.get("gameId"));
        gameStatsAreGeneratedFor = null;
        statsGenerationTask = null;
        return "gameDetails?faces-redirect=true&gameId=" + gameId;
    }

    public String clearGeneratedStats(boolean redirect){
        if(redirect){
            return clearGeneratedStats();
        }
        gameStatsAreGeneratedFor = null;
        statsGenerationTask = null;
        return null;
    }

    public boolean isStatsGenerationRunning(){
        return statsGenerationTask != null && !statsGenerationTask.isDone();
    }


    public List<PlayerGameStats> getGeneratedStats() throws ExecutionException, InterruptedException{
        if(statsGenerationTask == null || isStatsGenerationRunning()){
            return null;
        }
        return statsGenerationTask.get().getPlayerGameStatsList();
    }
}
