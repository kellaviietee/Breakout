package com.niilo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class BreakoutGame extends ApplicationAdapter {
	ShapeRenderer shape;
	private final int x = 50;
	private final int y = 50;

	@Override
	public void create () {
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.circle(50, 50, 50);
		shape.end();
	}
}
