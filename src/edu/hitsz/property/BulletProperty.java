package edu.hitsz.property;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.music.MusicThread;
import edu.hitsz.strategy.ScatterShoot;

public class BulletProperty extends AbstractProperty{

    public BulletProperty(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void getProperty(HeroAircraft heroAircraft) {
        System.out.println("FireSupply active!");
        if(Game.musicOn){
            new MusicThread("src/videos/get_supply.wav").start();
        }
        heroAircraft.setShootNum(3);
        heroAircraft.setStrategy(new ScatterShoot());
    }
}
