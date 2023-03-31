package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

public class BloodProperty extends AbstractProperty {
    //血包的加血量
    private final int BLOOD_UP = 40;
    public BloodProperty(int locationX, int locationY){
        super(locationX, locationY);
    }

    @Override
    public void getProperty(HeroAircraft heroAircraft) {
        heroAircraft.setHp(BLOOD_UP+heroAircraft.getHp());
        if(heroAircraft.getHp()>Game.HERO_HP){
            heroAircraft.setHp(Game.HERO_HP);
        }
    }
}
