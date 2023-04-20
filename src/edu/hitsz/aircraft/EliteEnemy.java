package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import edu.hitsz.application.StartMenu;
import edu.hitsz.factory.BloodPropertyFactory;
import edu.hitsz.factory.BombPropertyFactory;
import edu.hitsz.factory.BulletPropertyFactory;
import edu.hitsz.factory.PropertyFactory;
import edu.hitsz.property.AbstractProperty;

import java.util.List;
import java.util.Random;

/**
 * @author 210810401 石全
 */
public class EliteEnemy extends AbstractEnemyAircraft {

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, direction);
    }
    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public void produceProperty(List<AbstractProperty> properties, int locationX, int locationY){
        StartMenu.game.increaseScore(Game.ELITE_SCORE_UP);
        PropertyFactory propertyFactory = null;
        Random r = new Random();
        float randomNumber = r.nextFloat();
        if (randomNumber <= 0.3) {
            propertyFactory = new BloodPropertyFactory();
        } else if (randomNumber > 0.3 && randomNumber <= 0.6) {
            propertyFactory = new BulletPropertyFactory();
        } else if (randomNumber > 0.6 && randomNumber <= 0.9) {
            propertyFactory = new BombPropertyFactory();
        }
        if(propertyFactory!=null){
            properties.add(propertyFactory.createProperty(locationX, locationY));
        }
    }

    @Override
    public void update() {
        this.decreaseHp(Game.ELITE_HP);
        StartMenu.game.increaseScore(Game.ELITE_SCORE_UP);
    }
}
