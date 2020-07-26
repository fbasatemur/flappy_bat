package com.fbasatemur.flappy_bat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class FlappyBat extends ApplicationAdapter implements Start {
    SpriteBatch batch;

    Texture imgBackground;
    ArrayList<Texture> heroTexture;
    ArrayList<Texture> enemyTexture;

    State state = State.END;
    DifficultyLevel difficultyLevel = DifficultyLevel.VERYEASY;

    EnemyManage enemyManage;
    Hero hero;

    int scoreMultiplier = Start.scoreMultiplier;
    ArrayList<Enemy> enemies;
    Restart restart;
    BitmapFont scoreFont;
    Sound jumpSound;
    BitmapFont endFont;
    // ShapeRenderer sr;

    @Override
    public void create() {
        batch = new SpriteBatch();
        imgBackground = new Texture("level1_background.png");

	heroTexture = new ArrayList<Texture>();
        heroTexture.add(new Texture("hero1_1.png"));
        heroTexture.add(new Texture("hero1_2.png"));

        enemyTexture = new ArrayList<Texture>();
        enemyTexture.add(new Texture("enemy1_1.png"));
        enemyTexture.add(new Texture("enemy1_2.png"));

//		sr = new ShapeRenderer();
        enemyManage = new EnemyManage();
        enemies = new ArrayList<Enemy>();
        hero = new Hero();
        restart = new Restart();

        scoreFont = new BitmapFont();
        scoreFont.setColor(Color.FOREST);
        scoreFont.getData().setScale(Start.scoreFonSize);

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));

        endFont = new BitmapFont();
        endFont.setColor(Color.BROWN);
        endFont.getData().setScale(Start.endFontSize);
    }

    @Override
    public void render() {
        int time = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        batch.begin();
        batch.draw(imgBackground, 0, 0, Start.screenWidth, Start.screenHeight);
        batch.draw(heroTexture.get(time % 2), hero.getX(), hero.getY(), Start.birdWidth, Start.birdHeight);

        if (state == State.START) {

            if (Gdx.input.justTouched()) {
                hero.setDropRate(-Start.jumpSize);
                jumpSound.play();
            }

            if (hero.isKill()) {
                difficultyLevel = DifficultyLevel.VERYEASY;
                scoreMultiplier = Start.scoreMultiplier;
                restart.enemiesKill(enemies);
                state = State.PAUSE;

            } else {
                hero.dropRateUpper();
                scoreFont.draw(batch, String.valueOf("$" + hero.getScore()), Start.birdWidth, Start.screenHeight - birdHeight);

                for (int i = 0; i < enemies.size(); i++) {

                    if (enemies.get(i).isKill()) {

                        enemyManage.Refresh(enemies.get(i));
                        hero.incrementScore(scoreMultiplier);

                        switch (difficultyLevel.toString()) {
                            case "VERYEASY":
                                if (hero.getScore() == 20) {
                                    difficultyLevel = DifficultyLevel.EASY;
                                    scoreMultiplier = 3;
                                    Enemy.setHorizontalFast(15);
                                    enemyManage.Add(enemies);
                                }
                                break;
                            case "EASY":
                                if (hero.getScore() == 65) {
                                    difficultyLevel = DifficultyLevel.NORMAL;
                                    scoreMultiplier = 5;
                                    Enemy.setHorizontalFast(20);
                                    enemyManage.Add(enemies);
                                }
                                break;
                            case "NORMAL":
                                if (hero.getScore() == 140) {
                                    difficultyLevel = DifficultyLevel.HARD;
                                    scoreMultiplier = 7;
                                    Enemy.setHorizontalFast(25);
                                    enemyManage.Add(enemies);
                                }
                                break;
                            case "HARD":
                                if (hero.getScore() == 245) {
                                    difficultyLevel = DifficultyLevel.EXTREME;
                                    scoreMultiplier = 10;
                                    Enemy.setHorizontalFast(30);
                                    enemyManage.Add(enemies);
                                }
                                break;
                            case "EXTREME":
                                scoreMultiplier++;
                                break;

                            default:
                                break;
                        }
                    }

                    enemies.get(i).enemyMove();
                    batch.draw(enemyTexture.get(time % 2), enemies.get(i).getX(), enemies.get(i).getY(), Start.birdWidth, Start.birdHeight);
                }
            }

            //sr.begin(ShapeRenderer.ShapeType.Filled);
            //sr.setColor(Color.BLUE);
            //sr.circle(hero.getCircleX(), hero.getCircleY(), hero.getRadius());
            for (int i = 0; i < enemies.size(); i++) {

                hero.setCircle();
                enemies.get(i).setCircle();

                //	sr.circle(enemies.get(i).getCircleX(), enemies.get(i).getCircleY(), enemies.get(i).getRadius());

                if (Intersector.overlaps(hero.getCircle(), enemies.get(i).getCircle())) {
                    hero.setCrash(true);

                }
            }

            //sr.end();
        } else if (state == State.END) {
            endFont.draw(batch, "START", Start.screenWidth / 2, Start.screenHeight / 2);

            if (Gdx.input.justTouched()) {
                enemyManage.Create(enemies);
                state = State.START;
            }
        } else if (state == State.PAUSE) {
            endFont.draw(batch, "GAME OVER", Start.screenWidth / 2, Start.screenHeight / 2);
            endFont.draw(batch, "$" + hero.getScore(), Start.screenWidth / 2, Start.screenHeight / 3);

            if (Gdx.input.justTouched()) {
                restart.setHeroDefault(hero);
                state = State.END;

            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        jumpSound.dispose();
    }
}
