package pl.spoda.ks.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.spoda.ks.database.entity.MatchDetails;

import java.util.List;

@Repository
public interface MatchDetailsRepository extends JpaRepository<MatchDetails, Integer> {

    List<MatchDetails> findByMatchId(Integer matchId);

    @Query(value = """
            SELECT md.id from match_details md
                        where md.league_Id =:leagueId and md.player_Id=:playerId
                        order by md.id desc
                        fetch first row only
            """,
    nativeQuery = true)
    Integer findNewestPlayerMatchInLeague(@Param("playerId") Integer playerId, @Param("leagueId") Integer leagueId);
}
