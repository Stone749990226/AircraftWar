package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.strategy.DirectShoot;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyFactory implements EnemyFactory{
    @Override
    public AbstractEnemyAircraft createEnemy() {
        EliteEnemy eliteEnemy = new EliteEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                Game.ELITE_HP, 1, 30, 1);
        eliteEnemy.setStrategy(new DirectShoot());
        return eliteEnemy;
    }
}
