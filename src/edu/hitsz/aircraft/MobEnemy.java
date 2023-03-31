package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.property.AbstractProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemyAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, direction);
    }

    @Override
    public void produceProperty(List<AbstractProperty> properties, int locationX, int locationY) {

    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

/*
    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }
*/

}
