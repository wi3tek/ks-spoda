package pl.spoda.ks.api.season;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.spoda.ks.api.season.enums.PointCountingMethod;
import pl.spoda.ks.api.season.enums.RatingType;
import pl.spoda.ks.api.season.model.SeasonData;
import pl.spoda.ks.api.season.model.SeasonRequest;
import pl.spoda.ks.comons.date.DateService;
import pl.spoda.ks.comons.exceptions.SpodaApplicationException;
import pl.spoda.ks.comons.messages.InfoMessage;
import pl.spoda.ks.database.dto.SeasonDto;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonMapper {

    private final DateService dateService;

    @Value("${application.initial-rating}")
    public String initialRating;

    public SeasonDto mapSeason(SeasonRequest request) {
        SeasonDto seasonDto = SeasonDto.builder()
                .startDate( dateService.dateOf( request.getStartDate() ) )
                .leagueId( request.getLeagueId() )
                .isFinished( false )
                .pointCountingMethod( request.getPointCountingMethod().name() )
                .isEuro( request.getIsEuro() )
                .image( request.getImage() )
                .seasonName(request.getSeasonName())
                .build();

        if (PointCountingMethod.RATING.equals( request.getPointCountingMethod() )) {

            seasonDto.setInitialRating( new BigDecimal( initialRating ) );
            seasonDto.setRatingType( Optional.ofNullable( request.getRatingType() ).map( Enum::name )
                    .orElseThrow( () -> new SpodaApplicationException( InfoMessage.RATING_TYPE_REQUIRED ) ) );
        }
        return seasonDto;
    }

    public List<SeasonData> mapToSeasonList(List<SeasonDto> seasonDtoList) {
        return seasonDtoList.stream()
                .map( this::mapSeason )
                .sorted( Comparator.comparing( SeasonData::getSeasonCount ).reversed() )
                .toList();
    }

    public SeasonData mapSeason(SeasonDto seasonDto) {
        return SeasonData.builder()
                .id( seasonDto.getId() )
                .startDate( seasonDto.getStartDate() )
                .endDate( seasonDto.getEndDate() )
                .leagueId( seasonDto.getLeagueId() )
                .isFinished( seasonDto.getIsFinished() )
                .initialRating( seasonDto.getInitialRating() )
                .pointCountingMethod( PointCountingMethod.getByName( seasonDto.getPointCountingMethod() ) )
                .seasonCount( seasonDto.getLeagueSeasonCount() )
                .ratingType( RatingType.getByName( seasonDto.getRatingType() ) )
                .isEuro( seasonDto.getIsEuro() )
                .image(seasonDto.getImage())
                .seasonName(seasonDto.getSeasonName())
                .build();
    }
}
