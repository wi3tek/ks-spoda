package pl.spoda.ks.euro.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class EuroMatch {

    private Integer matchNumber; // numer meczu z oficjalnego kalendarza UEFA
    private TournamentStage tournamentStage;
    private TournamentGroup tournamentGroup;
    private boolean played;
    private Team homeTeam;
    private Integer homeTeamId;
    private Team awayTeam;
    private Integer awayTeamId;
    private Integer homeGoals;
    private Integer awayGoals;
    private List<Player> homePlayers;
    private List<Player> awayPlayers;
    private LocalDateTime dateTime;
    private String playedCity;
    private LocalDateTime created;
    private LocalDateTime updated;

}
