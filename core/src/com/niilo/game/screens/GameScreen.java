package com.niilo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.niilo.game.BreakoutGame;
import com.niilo.game.actors.BreakoutActor;

public class GameScreen implements Screen {

    private final Stage stage;
    private final Label nameLabel;

    private final Label scoreLabel;

    public GameScreen(BreakoutGame breakout, String name, Skin skin) {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        nameLabel = new Label(name, skin);
        scoreLabel = new Label("Score: 0", skin);
        nameLabel.setPosition(10, Gdx.graphics.getHeight() - 30);
        scoreLabel.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 30);
        BreakoutActor breakoutActor = new BreakoutActor(breakout, name, breakout.shape, scoreLabel);
        stage.addActor(nameLabel);
        stage.addActor(scoreLabel);
        stage.addActor(breakoutActor);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
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
