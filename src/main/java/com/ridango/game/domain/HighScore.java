package com.ridango.game.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "HIGH_SCORE", schema = "PUBLIC")
@AllArgsConstructor
public class HighScore {

    @Id
    private long id;
    private long score;
    private String playerName;
}
