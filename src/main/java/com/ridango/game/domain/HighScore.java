package com.ridango.game.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HIGH_SCORE", schema = "PUBLIC")
public class HighScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "SCORE")
    private long score;

    @Column(name = "PLAYERNAME")
    private String playerName;

    @Column(name = "DATE_DELETED")
    private Timestamp dateDeleted;
}