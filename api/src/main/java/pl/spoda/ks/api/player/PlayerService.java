package pl.spoda.ks.api.player;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.spoda.ks.api.commons.BaseResponse;
import pl.spoda.ks.api.commons.DeleteResponse;
import pl.spoda.ks.api.commons.ResponseResolver;
import pl.spoda.ks.api.player.model.*;
import pl.spoda.ks.comons.aspects.LogEvent;
import pl.spoda.ks.comons.exceptions.SpodaApplicationException;
import pl.spoda.ks.comons.messages.InfoMessage;
import pl.spoda.ks.database.dto.PlayerDto;
import pl.spoda.ks.database.service.PlayerServiceDB;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    private final PlayerServiceDB playerServiceDB;
    private final PlayerMapper playerMapper;
    private final ResponseResolver responseResolver;

    @LogEvent
    public ResponseEntity<BaseResponse> addPlayer(PlayerRequest playerRequest) {
        Integer newPlayerId;
        try {
            newPlayerId = playerServiceDB.add( playerMapper.mapToPlayerDto( playerRequest ) );
        } catch (SpodaApplicationException e) {
            log.error( e.getMessage(), e );
            return responseResolver.prepareResponse( HttpStatus.CONFLICT, InfoMessage.ALIAS_ALREADY_EXISTS );
        }
        return responseResolver.prepareResponseCreated( StoredPlayer.builder().playerId( newPlayerId ).build() );
    }

    @LogEvent
    public ResponseEntity<BaseResponse> getPlayers() {
        List<PlayerDto> playerList = playerServiceDB.getPlayerList();
        PlayerListResponse response = PlayerListResponse.builder()
                .players( playerMapper.mapToPlayerDataList(playerList))
                .build();

        if(playerList.isEmpty()) {
            response.setErrorMessage( InfoMessage.NO_PLAYERS_FOUND );
        }

        return responseResolver.prepareResponse( response );
    }

    @LogEvent
    public ResponseEntity<BaseResponse> getPlayer(Integer playerId) {
        PlayerData playerData = playerMapper.mapToPlayerData(playerServiceDB.getPlayer(playerId));
        return responseResolver.prepareResponse( PlayerResponse.builder().player( playerData ).build() );
    }

    @LogEvent
    public ResponseEntity<BaseResponse> editPlayer(Integer playerId, PlayerRequest playerRequest) {
        try {
            playerServiceDB.editPlayer(playerId, playerMapper.mapToPlayerDto( playerRequest ));
        } catch (Exception e) {
            log.error( e.getMessage(),e );
            return responseResolver.prepareResponse( HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }

        return responseResolver.prepareResponse( StoredPlayer.builder().playerId( playerId ).build() );
    }

    @LogEvent
    public ResponseEntity<BaseResponse> deletePlayer(Integer playerId) {
        playerServiceDB.deletePlayer(playerId);
        return responseResolver.prepareResponse( DeleteResponse.builder().deletedId( playerId ).build() );
    }

    @LogEvent
    public ResponseEntity<BaseResponse> getPlayersByLeague(Integer leagueId) {
        List<PlayerDto> playerDtos = playerServiceDB.getPlayerListByLeagueId(leagueId);
        Set<PlayerData> players = playerMapper.mapToPlayerDataList( playerDtos );
        return responseResolver.prepareResponse( PlayerListResponse.builder().players( players ).build() );
    }
}
