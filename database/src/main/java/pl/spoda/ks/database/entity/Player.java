package pl.spoda.ks.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@Table(name = "PLAYER")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Setter
public class Player extends BaseEntity {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="NAME",nullable = false)
    private String name;
    @Column(name="ALIAS", unique = true)
    private String alias;
    @Column(name="PLAYER_IMG")
    private String playerImg;
    @Column(name="DESCRIPTION")
    private String desc;

    @OneToMany(mappedBy = "player")
    @ToString.Exclude
    private List<SeasonTable> seasonTable;

    @OneToMany(mappedBy = "player")
    @ToString.Exclude
    private List<LeagueTable> leagueTable;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="LEAGUE_TABLE",
            joinColumns = @JoinColumn(name="player_id"),
            inverseJoinColumns = @JoinColumn(name="league_id"))
    @ToString.Exclude
    private Set<League> leagues;

    @ManyToMany(mappedBy = "teamPlayers", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Transient
    private Set<MatchTeam> matchTeams;
}
