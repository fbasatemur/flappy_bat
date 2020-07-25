package com.fbasatemur.flappy_bat;

public class Enemy extends Bird implements Start {
    private static float horizontalFast;

    public Enemy(float X, float Y) {
        this.setX(X);
        this.setY(Y);
        this.setCircle();
    }

    public static void setHorizontalFast(float horizontalFastValue) {
        horizontalFast = horizontalFastValue;
    }

    public void enemyMove() {
        this.setX(this.getX() - this.horizontalFast);
    }

    public boolean isKill() {
        return this.getX() <= -1 * (Start.birdWidth);
    }
}
