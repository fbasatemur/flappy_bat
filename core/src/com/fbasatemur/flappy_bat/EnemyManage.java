package com.fbasatemur.flappy_bat;

import java.util.ArrayList;
import java.util.Random;

public class EnemyManage implements Start {
    private Random rand = new Random();

    public void Create(ArrayList<Enemy> enemies) {
        Enemy.setHorizontalFast(Start.horizontalFast);
        for (int i = 0; i < Start.numberOfEnemies; i++) {
            enemies.add(new Enemy(Start.screenWidth + Start.birdWidth * rand.nextFloat() * 10.0F, rand.nextFloat() * Start.screenHeight));
        }
    }

    public void Add(ArrayList<Enemy> enemies) {
        enemies.add(new Enemy(Start.screenWidth + Start.birdWidth * rand.nextFloat() * 10.0F, rand.nextFloat() * Start.screenHeight));
    }

    public void Refresh(Enemy enemy) {
        enemy.setX(Start.screenWidth + 10.0F * Start.birdWidth * rand.nextFloat());
        enemy.setY(Start.maxBirdHeight * rand.nextFloat());
    }

}

