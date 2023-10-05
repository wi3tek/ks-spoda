package pl.spoda.ks.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

    @OneToOne(mappedBy = "player")
    private SeasonRating seasonRating;

    @OneToOne(mappedBy = "player")
    private LeagueRating leagueRating;
}
