package edu.hitsz.aircraft;

public class EliteEnemyFactory implements EnemyFactory{
    @Override
    public AbstractEnemyAircraft CreateEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
