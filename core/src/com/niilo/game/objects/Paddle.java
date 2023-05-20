package com.niilo.game.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {

    private float x;
    private final float y;
    private final float width;
    private final float height;

    public Paddle(float x, int y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, width, height);
    }

    public void update(float x) {
        this.x = x - width / 2;
    }
}
