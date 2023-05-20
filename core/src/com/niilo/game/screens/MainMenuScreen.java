package com.niilo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.niilo.game.BreakoutGame;

public class MainMenuScreen implements Screen {

    final BreakoutGame breakout;
    OrthographicCamera camera;

    public MainMenuScreen(BreakoutGame breakout) {
        this.breakout = breakout;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        breakout.batch.setProjectionMatrix(camera.combined);
        breakout.batch.begin();
        breakout.font.draw(breakout.batch, "Welcome to Drop!!! ", camera.viewportWidth / 2, camera.viewportHeight / 2);
        breakout.font.draw(breakout.batch, "Tap anywhere to begin!", 100, 100);
        breakout.batch.end();

        if (Gdx.input.isTouched()) {
            breakout.setScreen(new GameScreen(breakout));
            dispose();
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
}
