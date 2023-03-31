package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.strategy.Strategy;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    private Strategy strategy;
    protected int maxHp;
    protected int hp;

    /**
     * 子弹一次发射数量
     */
    protected int shootNum;
    /**
     * 子弹伤害
     */
    protected int power;
    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    protected int direction;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease) {
        hp -= decrease;
        if (hp <= 0) {
            hp = 0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }


    /**
     * 飞机射击方法，可射击对象必须实现
     *
     * @return 可射击对象需实现，返回子弹
     * 非可射击对象空实现，返回null
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        return strategy.shoot(aircraft);
    }
//    public abstract List<BaseBullet> shoot();

    public int getShootNum() {
        return shootNum;
    }

    public int getPower() {
        return power;
    }

    public int getDirection() {
        return direction;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }
}


