package com.fbasatemur.flappy_bat;

import com.badlogic.gdx.Gdx;

public interface Start {

    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    float X = screenWidth / 4;
    float Y = screenHeight / 3;
    float birdWidth = screenWidth / 15;
    float birdHeight = screenHeight / 10;
    float Gravity = 1.5F;
    float DropRate = 1.0F;
    float horizontalFast = 10.0F;
    int scoreMultiplier = 1;
    int numberOfEnemies = 5;
    float maxBirdHeight = screenHeight - birdHeight;
    float jumpSize = birdHeight / 4.5F;
    int scoreFonSize = 7;
    int endFontSize = 9;

}
