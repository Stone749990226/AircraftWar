package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class BossEnemy extends AbstractEnemyAircraft{
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void produceProperty(List<AbstractProperty> properties, int locationX, int locationY) {

    }

    @Override
    public List<BaseBullet> shoot() {
        return null;
    }
}
