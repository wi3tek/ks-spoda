package pl.spoda.ks.api.league.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.spoda.ks.api.commons.BaseResponse;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LeagueListResponse extends BaseResponse {

    private List<LeagueData> leagues;
}
