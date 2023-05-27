package com.niilo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.niilo.game.BreakoutGame;

public class MainMenuScreen implements Screen {

    final BreakoutGame breakout;
    OrthographicCamera camera;
    private final Stage stage;
    private TextField nameField;
    TextButton playButton;
    private final Skin skin;

    public MainMenuScreen(BreakoutGame breakout) {
        this.breakout = breakout;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport(camera));
        skin = new Skin(Gdx.files.internal("uiskin.json"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table(); // Create a new table
        table.setFillParent(true); // Set the table to fill the parent container
        Label titleLabel = new Label("Welcome to breakout!", skin);
        titleLabel.setFontScale(2.0f);
        nameField = new TextField("", skin);
        nameField.setAlignment(1);
        playButton = new TextButton("Play", skin);
        makePlayButtonChangeScenes();
        table.add(titleLabel).padBottom(10f).row();
        table.add(nameField).padBottom(10f).row(); // Add the name field with bottom padding
        table.add(playButton); // Add the play button
        stage.addActor(table); // Add the table to the stage
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        // Update the stage
        stage.act(delta);
        // Render the stage
        stage.draw();
        camera.update();
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
        stage.dispose();
    }

    private void makePlayButtonChangeScenes() {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                breakout.setScreen(new GameScreen(breakout, nameField.getText()));
                dispose();
            }
        });
    }
}
