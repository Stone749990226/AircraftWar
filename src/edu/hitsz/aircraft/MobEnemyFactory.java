package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyFactory implements EnemyFactory {
    @Override
    public AbstractEnemyAircraft createEnemy() {
        return new MobEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                Game.MOB_HP);
    }
}
