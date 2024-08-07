package pl.spoda.ks.database.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import pl.spoda.ks.comons.aspects.LogEvent;
import pl.spoda.ks.comons.exceptions.SpodaApplicationException;
import pl.spoda.ks.comons.exceptions.SpodaDatabaseException;
import pl.spoda.ks.comons.messages.InfoMessage;
import pl.spoda.ks.database.dto.MatchDayDto;
import pl.spoda.ks.database.entity.MatchDay;
import pl.spoda.ks.database.entity.Season;
import pl.spoda.ks.database.mapper.EntityMapper;
import pl.spoda.ks.database.repository.MatchDayRepository;
import pl.spoda.ks.database.repository.SeasonRepository;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchDayServiceDB {

    private final BaseServiceDB baseServiceDB;
    private final MatchDayRepository matchDayRepository;
    private final EntityMapper entityMapper = Mappers.getMapper( EntityMapper.class );
    private final SeasonRepository seasonRepository;
    private final MatchServiceDB matchServiceDB;

    @Transactional
    @LogEvent
    public MatchDayDto createMatchDay(MatchDayDto matchDayDto) {
        Season storedSeason = seasonRepository.findById( matchDayDto.getSeasonId() ).orElse( null );
        validateSeason( matchDayDto, storedSeason );
        Integer seasonId = storedSeason.getId();
        List<MatchDay> storedMatchDay = matchDayRepository.findBySeasonId( seasonId );
        validateMatchDay( matchDayDto, storedMatchDay, seasonId );

        MatchDay matchDay = entityMapper.mapToMatchDay( matchDayDto );
        matchDay.isFinished( false );
        matchDay.season( storedSeason );
        matchDay.seasonMatchDay( storedMatchDay.size() + 1 );
        baseServiceDB.createEntity( matchDay );

        MatchDay save = matchDayRepository.save( matchDay );
        return entityMapper.mapToMatchDayDto( save );
    }

    @LogEvent
    public List<MatchDayDto> getMatchDaysBySeasonId(Integer seasonId) {
        List<MatchDay> storedMatchDay = matchDayRepository.findBySeasonId( seasonId );

        return storedMatchDay.stream()
                .map( entityMapper::mapToMatchDayDto )
                .sorted( Comparator.comparing( MatchDayDto::getSeasonMatchDay ).reversed() )
                .toList();
    }

    @LogEvent
    public MatchDayDto getMatchDay(Integer matchDayId) {
        MatchDay storedMathDay = matchDayRepository.findById( matchDayId ).orElse( null );
        if (storedMathDay == null)
            throw new SpodaApplicationException( String.format( "There is no match day with id: %d", matchDayId ) );

        return entityMapper.mapToMatchDayDto( storedMathDay );
    }

    private void validateMatchDay(
            MatchDayDto matchDayDto,
            List<MatchDay> storedMatchDays,
            Integer seasonId
    ) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "d MMMM yyyy" ,new Locale("pl"));
        String lastMatchDayDate  = storedMatchDays.stream()
                .map( MatchDay::getDate )
                .max( Comparator.naturalOrder() )
                .map( x -> x.format( dateTimeFormatter ) )
                .orElse( null );

        Integer matchDaysBeforeRequestedDate = matchDayRepository.findBySeasonIdAndDateBefore( matchDayDto.getDate(), seasonId );
        if (matchDaysBeforeRequestedDate != 0) {

            throw new SpodaApplicationException( String.format( "Istnieją już kolejki rozegrane do dnia: %s",
                    lastMatchDayDate ) );
        }

        if (isAnyMatchDayUnfinished( storedMatchDays ))
            throw new SpodaApplicationException(  InfoMessage.UNFINISHED_MATCH_DAYS);

        if (storedMatchDays.stream()
                .map( MatchDay::getDate )
                .anyMatch( matchDayDate -> matchDayDate.isAfter( matchDayDto.getDate() ) )) {
            throw new SpodaApplicationException(
                    String.format( "MatchDay date cannot be earlier than any other match days in the season: %s",
                            seasonId ) );
        }
    }

    private void validateSeason(MatchDayDto matchDayDto, Season storedSeason) {
        if (storedSeason == null)
            throw new SpodaApplicationException( String.format( "Season with id: %d is not exist",
                    matchDayDto.getSeasonId() ) );

        if (BooleanUtils.isTrue( storedSeason.getIsFinished() ))
            throw new SpodaApplicationException( String.format( "Season %d is finished. Could not edit it",
                    matchDayDto.getSeasonId() ) );
    }

    private boolean isAnyMatchDayUnfinished(List<MatchDay> storedMatchDay) {
        return storedMatchDay.stream()
                .anyMatch( matchDay -> BooleanUtils.isNotTrue( matchDay.getIsFinished() ) );
    }

    public boolean isAnyMatchDayUnfinished(Integer seasonId) {
        return matchDayRepository.findBySeasonId( seasonId ).stream()
                .anyMatch( matchDay -> BooleanUtils.isNotTrue( matchDay.getIsFinished() ) );
    }

    public void completeMatchDay(Integer matchDayId) {
        matchServiceDB.validateUnfinishedMatchDayMatches( matchDayId );

        MatchDay storedMatchDay = matchDayRepository.findById( matchDayId ).orElse( null );
        checkIfMatchDayExists( matchDayId, storedMatchDay );
        storedMatchDay.isFinished( true );
        baseServiceDB.updateEntity( storedMatchDay );
        matchDayRepository.save( storedMatchDay );
    }

    private void checkIfMatchDayExists(Integer matchDayId, MatchDay storedMatchDay) {
        if (storedMatchDay == null)
            throw new SpodaApplicationException( InfoMessage.getMessage( InfoMessage.LEAGUE_NOT_FOUND, matchDayId.toString() ) );
    }


    @Transactional
    public void deleteMatchDay(Integer matchDayId) {
        Optional<MatchDay> storedMatchDay = matchDayRepository.findById( matchDayId );

        if (storedMatchDay.isEmpty())
            throw new SpodaApplicationException( "There is no matchDay withId: " + matchDayId );

        try {
            storedMatchDay.ifPresent( matchDayRepository::delete );
        } catch (SpodaDatabaseException e) {
            log.error( e.getMessage() );
        }
        List<MatchDay> storedMatchDays = matchDayRepository.findBySeasonId( storedMatchDay.get().getSeasonId() )
                .stream()
                .sorted( Comparator.comparing( MatchDay::getDate ) )
                .toList();

        IntStream.range( 0, storedMatchDays.size() )
                .forEach( i -> storedMatchDays.get( i ).seasonMatchDay( i + 1 ) );
        matchDayRepository.saveAll( storedMatchDays );
    }
}
