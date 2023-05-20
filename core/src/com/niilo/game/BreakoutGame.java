package com.niilo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.niilo.game.screens.MainMenuScreen;

public class BreakoutGame extends Game {
	public ShapeRenderer shape;
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create() {
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	public void dispose() {
		shape.dispose();
		batch.dispose();
		font.dispose();
	}
}
