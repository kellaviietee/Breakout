package com.niilo.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x < size || x > Gdx.graphics.getWidth() - size) {
            reverseXSpeed();
        }
        if (y < size || y > Gdx.graphics.getHeight() - size) {
            reverseYSpeed();
        }
    }

    private void reverseYSpeed() {
        ySpeed = -ySpeed;
    }

    private void reverseXSpeed() {
        xSpeed = -xSpeed;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle) {
        if (collidesWith(paddle)) {
            reverseYSpeed();
        }
    }

    private boolean collidesWith(Paddle paddle) {
        boolean touchesFromLeft = (paddle.getX() + paddle.getWidth()) > x - size;
        boolean touchesFromRight = (paddle.getX()) < x + size;
        boolean horizontalCollision = touchesFromLeft && touchesFromRight;
        boolean touchesFromBelow = ((paddle.getY() + paddle.getHeight()) > (y - size));
        boolean touchesFromAbove = paddle.getY() < y + size;
        boolean verticalCollision = touchesFromBelow && touchesFromAbove;
        return horizontalCollision && verticalCollision;
    }

    public void checkCollision(Block block) {
        if (collidesWith(block)) {
            ySpeed = -ySpeed;
            block.setDestroyed(true);
        }
    }

    private boolean collidesWith(Block block) {
        boolean touchesFromLeft = (block.getX() + block.getWidth()) > x - size;
        boolean touchesFromRight = (block.getX()) < x + size;
        boolean horizontalCollision = touchesFromLeft && touchesFromRight;
        boolean touchesFromBelow = ((block.getY() + block.getHeight()) > (y - size));
        boolean touchesFromAbove = block.getY() < y + size;
        boolean verticalCollision = touchesFromBelow && touchesFromAbove;
        return horizontalCollision && verticalCollision;
    }
}