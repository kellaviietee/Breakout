package com.niilo.game.backend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Long id;
    private final String name;
    private final List<HighScore> highScores = new ArrayList<>();

    @JsonCreator
    public Player(Long id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<HighScore> getHighScores() {
        return highScores;
    }
}