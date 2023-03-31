package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.strategy.ScatterShoot;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyFactory implements EnemyFactory {
    @Override
    public AbstractEnemyAircraft createEnemy() {
        BossEnemy bossEnemy = new BossEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                10,
                0,
                Game.BOSS_HP, 3, 30, 1);
        bossEnemy.setStrategy(new ScatterShoot());
        return bossEnemy;
    }
}
