package com.fbasatemur.flappy_bat;

import java.util.ArrayList;

public class Restart {

    public void setHeroDefault(Hero heroTemp) {
        heroTemp.setStartDefault();
    }

    public void enemiesKill(ArrayList<Enemy> enemyTemp) {
        enemyTemp.clear();
    }

}
