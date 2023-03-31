package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;

public interface EnemyFactory {
    AbstractEnemyAircraft createEnemy();
}
