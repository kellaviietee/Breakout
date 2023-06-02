package com.niilo.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.niilo.game.BreakoutGame;
import com.niilo.game.http.RequestSender;
import com.niilo.game.objects.Ball;
import com.niilo.game.objects.Block;
import com.niilo.game.objects.Paddle;
import com.niilo.game.screens.MainMenuScreen;

import java.util.ArrayList;
import java.util.List;

public class BreakoutActor extends Actor {

    private final BreakoutGame breakout;
    private final Ball ball = new Ball(200, 200, 10, 5, 5);
    private final Paddle paddle = new Paddle(500, 10, 100, 15);
    private boolean isPaused = false;
    private final String name;
    private final ShapeRenderer shape;
    private final Label scoreLabel;
    private final List<Block> blocks = new ArrayList<>();

    private int totalScore = 0;
    private int breakingScore = 0;

    public BreakoutActor(BreakoutGame breakout, String name, ShapeRenderer shape, Label scoreLabel) {
        this.breakout = breakout;
        this.name = name;
        this.shape = shape;
        this.scoreLabel = scoreLabel;
        int blockWidth = 63;
        int blockHeight = 20;
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight() - 40; y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        if (!isPaused) {
            gameLoop();
        } else {
            endGame();
        }
        batch.begin();
    }

    private void gameLoop() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        if (ball.checkCollision(paddle)) {
            addScoreToPlayer();
        } else if (ball.collidedWithFloor()) {
            resetScore();
        }
        paddle.draw(shape);
        paddle.update(Gdx.input.getX());
        ball.draw(shape);
        ball.update();
        collideWithBlocks();
        shape.end();
    }

    private void resetScore() {
        breakingScore = 0;
    }

    private void addScoreToPlayer() {
        totalScore += breakingScore;
        scoreLabel.setText("Score: " + totalScore);
        resetScore();
    }

    private void endGame() {
        breakout.setScreen(new MainMenuScreen(breakout));
        RequestSender.sendHighScore(totalScore, name);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private void collideWithBlocks() {
        if (!blocks.isEmpty()) {
            for (Block block : blocks) {
                block.draw(shape);
                if (ball.checkCollision(block)) {
                    breakingScore += 100;
                }
            }
            for (int i = 0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                if (b.isDestroyed()) {
                    blocks.remove(b);
                    i--;
                }
            }
            if (blocks.size() <= 50) {
                isPaused = true;
            }
        } else {
            isPaused = true;
        }
    }
}
