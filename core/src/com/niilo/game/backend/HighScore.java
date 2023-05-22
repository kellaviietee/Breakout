package com.niilo.game.backend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HighScore {
    private final Integer score;
    private final Player player;

    @JsonCreator
    public HighScore(@JsonProperty("score") Integer score, @JsonProperty("player") Player player) {
        this.score = score;
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }
}
