package pl.spoda.ks.api.player.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.spoda.ks.api.commons.BaseResponse;

import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerListResponse extends BaseResponse {

    Set<PlayerData> players;
}
