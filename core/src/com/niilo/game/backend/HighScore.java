package com.niilo.game.backend;

public class HighScore {
    private final Integer score;
    private final Player player;


    public HighScore(Integer score, Player player) {
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
