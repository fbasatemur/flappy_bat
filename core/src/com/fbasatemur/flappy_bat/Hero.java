package com.fbasatemur.flappy_bat;

public class Hero extends Bird implements Start {

    protected float gravity = 1.5F;
    protected float dropRate = 0.0F;
    private int score = 0;
    private boolean isCrashed = false;

    public Hero() {
        this.setX(Start.X);
        this.setY(Start.Y);
        this.setCircle();
    }

    public void setDropRate(float dropRate) {
        this.dropRate = dropRate;
    }

    public void dropRateUpper() {
        this.dropRate = this.dropRate + this.gravity;
        if (this.getY() - this.dropRate < Start.maxBirdHeight)
            this.setY(this.getY() - this.dropRate);
    }

    public void setStartDefault() {
        this.setX(Start.X);
        this.setY(Start.Y);
        this.gravity = Start.Gravity;
        this.dropRate = Start.DropRate;
        this.score = 0;
        this.isCrashed = false;
    }

    public boolean isKill() {
        return this.getY() <= 0 || this.isCrashed;
    }

    public void setCrash(boolean crashed) {
        this.isCrashed = crashed;
    }

    public int getScore() {
        return this.score;
    }

    public void incrementScore(int scoreMultiplier) {
        this.score += scoreMultiplier;
    }
}

