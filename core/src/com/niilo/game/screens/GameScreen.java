package com.niilo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.niilo.game.BreakoutGame;
import com.niilo.game.objects.Ball;
import com.niilo.game.objects.Block;
import com.niilo.game.objects.Paddle;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    ShapeRenderer shape;
    private final List<Block> blocks = new ArrayList<>();
    private final BreakoutGame breakout;
    private final Ball ball = new Ball(200, 200, 10, 5, 5);
    private final Paddle paddle = new Paddle(500, 10, 100, 15);
    private boolean isPaused = false;

    public GameScreen(BreakoutGame breakout) {
        this.breakout = breakout;
        this.shape = breakout.shape;
        int blockWidth = 63;
        int blockHeight = 20;
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (!isPaused) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            ball.checkCollision(paddle);
            paddle.draw(shape);
            paddle.update(Gdx.input.getX());
            ball.draw(shape);
            ball.update();
            collideWithBlocks();
            shape.end();
        } else {
            breakout.setScreen(new MainMenuScreen(breakout));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void collideWithBlocks() {
        if (!blocks.isEmpty()) {
            for (Block block : blocks) {
                block.draw(shape);
                ball.checkCollision(block);
            }
            for (int i = 0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                if (b.isDestroyed()) {
                    blocks.remove(b);
                    i--;
                }
            }
        } else {
            isPaused = true;
        }
    }
}
