package edu.hitsz.property;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.StartMenu;
import edu.hitsz.application.Subscriber;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.music.MusicThread;

import java.util.ArrayList;
import java.util.List;

public class BombProperty extends AbstractProperty{
    public BombProperty(int locationX, int locationY) {
        super(locationX, locationY);
    }
    private static ArrayList<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber s){
        subscribers.add(s);
    }

    public void unsubscribeAll(){
        subscribers.clear();
    }

    public void notifyAllSubscriber(){
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    @Override
    public void getProperty(HeroAircraft heroAircraft) {
        System.out.println("BombSupply active!");
        if(Game.musicOn){
            new MusicThread("src/videos/bomb_explosion.wav").start();
        }
        List<AbstractEnemyAircraft> enemyAircrafts = StartMenu.game.getEnemyAircrafts();
        List<BaseBullet> enemyBullets = StartMenu.game.getEnemyBullets();
        for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
            subscribe(enemyAircraft);
        }
        for (BaseBullet enemyBullet : enemyBullets) {
            subscribe(enemyBullet);
        }
        notifyAllSubscriber();
        unsubscribeAll();
    }
}
