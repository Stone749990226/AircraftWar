package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.factory.BloodPropertyFactory;
import edu.hitsz.factory.BombPropertyFactory;
import edu.hitsz.factory.BulletPropertyFactory;
import edu.hitsz.factory.PropertyFactory;
import edu.hitsz.property.AbstractProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author 210810401 石全
 */
public class BossEnemy extends AbstractEnemyAircraft {
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, direction);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    @Override
    public void produceProperty(List<AbstractProperty> properties, int locationX, int locationY) {
        PropertyFactory propertyFactory = null;
        Random r = new Random();
        float randomNumber;
        for (int i = 0; i < 3; i++) {
            randomNumber = r.nextFloat();
            if (randomNumber <= 0.33) {
                propertyFactory = new BloodPropertyFactory();
            } else if (randomNumber > 0.33 && randomNumber <= 0.66) {
                propertyFactory = new BulletPropertyFactory();
            } else if (randomNumber > 0.66) {
                propertyFactory = new BombPropertyFactory();
            }
            properties.add(propertyFactory.createProperty(locationX-(i-1)*30, locationY));
        }
    }

/*    *//**
     * 通过射击产生子弹
     *
     * @return 射击出的子弹List
     *//*
    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        int speedY = this.getSpeedY() + direction * 5;
        BaseBullet bullet;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            int speedX = i - 1;
            bullet = new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }*/
}
