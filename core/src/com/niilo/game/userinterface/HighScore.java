package com.niilo.game.userinterface;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HighScore {

    private final Integer score;
    @JsonCreator
    public HighScore(@JsonProperty("score")Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}
