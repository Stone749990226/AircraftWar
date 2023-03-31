package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyFactory implements EnemyFactory {
    @Override
    public AbstractEnemyAircraft createEnemy() {
        return new BossEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                10,
                0,
                Game.BOSS_HP);
    }
}
