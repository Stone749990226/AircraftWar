package edu.hitsz.aircraft;

import edu.hitsz.application.Subscriber;
import edu.hitsz.property.AbstractProperty;

import java.util.List;

public abstract class AbstractEnemyAircraft extends AbstractAircraft implements Subscriber {


    public AbstractEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = shootNum;
        this.power = power;
        this.direction = direction;
    }

    public abstract void produceProperty(List<AbstractProperty> properties, int locationX, int locationY);


}
