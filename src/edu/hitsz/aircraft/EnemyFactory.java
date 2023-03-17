package edu.hitsz.aircraft;

public interface EnemyFactory {
    AbstractEnemyAircraft CreateEnemy(int locationX, int locationY, int speedX, int speedY, int hp);
}
