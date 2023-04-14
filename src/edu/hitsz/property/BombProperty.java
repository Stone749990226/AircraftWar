package edu.hitsz.property;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.music.MusicThread;

public class BombProperty extends AbstractProperty{
    public BombProperty(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void getProperty(HeroAircraft heroAircraft) {
        System.out.println("BombSupply active!");
        if(Game.musicOn){
            new MusicThread("src/videos/bomb_explosion.wav").start();
        }
    }
}
