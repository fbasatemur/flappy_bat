package com.fbasatemur.flappy_bat;

import com.badlogic.gdx.math.Circle;

public class Bird implements Start {
    private float X;
    private float Y;
    private Circle circle = new Circle();

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public float getRadius() {
        return Start.birdWidth / 2.5F;
    }

    public float getCircleX() {
        return this.X + Start.birdWidth / 2;
    }

    public float getCircleY() {
        return this.Y + Start.birdHeight / 2;
    }

    public void setCircle() {
        this.circle.set(this.X + Start.birdWidth / 2, this.Y + Start.birdHeight / 2, Start.birdWidth / 2.5F);
    }
}
