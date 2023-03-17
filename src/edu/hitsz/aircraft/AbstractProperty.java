package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

public abstract class AbstractProperty extends AbstractFlyingObject {
    public AbstractProperty(int locationX, int locationY) {
        super(locationX, locationY, 0, 10);
    }
    public abstract void getProperty(HeroAircraft heroAircraft);
    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
}
