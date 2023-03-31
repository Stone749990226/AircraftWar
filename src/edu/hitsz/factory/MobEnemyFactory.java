package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.strategy.NoneShoot;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyFactory implements EnemyFactory {
    @Override
    public AbstractEnemyAircraft createEnemy() {
        MobEnemy mobEnemy = new MobEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                Game.MOB_HP, 0, 0, 1);
        mobEnemy.setStrategy(new NoneShoot());
        return mobEnemy;
    }
}
