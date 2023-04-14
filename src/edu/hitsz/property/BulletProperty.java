package edu.hitsz.property;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.music.MusicThread;
import edu.hitsz.strategy.DirectShoot;
import edu.hitsz.strategy.ScatterShoot;

import java.util.concurrent.TimeUnit;

public class BulletProperty extends AbstractProperty{
    /**
     * 这里引入了hashCode,是为了处理英雄机在火力道具生效期内,又重新获得火力道具的情况.正常来说时间应该叠加
     * 比如持续时间为5s的火力加成,在持续了3s后又捡到了一个火力道具,此时要再过5s再重新修改英雄机的射击策略
     * 所以在修改射击策略时,需要检测hashCode是否一致
     */
    private static long hashCode;

    public BulletProperty(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void getProperty(HeroAircraft heroAircraft) {
        System.out.println("FireSupply active!");
        hashCode = this.hashCode();
        BulletProperty.hashCode = this.hashCode();
        if(Game.musicOn){
            new MusicThread("src/videos/get_supply.wav").start();
        }
        heroAircraft.setShootNum(3);
        heroAircraft.setStrategy(new ScatterShoot());
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                //在修改射击策略时,需要检测hashCode是否一致,不然可能会出现连续捡到两个火力道具时,第一个火力道具线程将射击策略改为直射,另外一个仍在散射中
                if(BulletProperty.hashCode == this.hashCode()){
                    heroAircraft.setStrategy(new DirectShoot());
                    heroAircraft.setShootNum(1);
                }
            } catch (InterruptedException e) {

            }
        }).start();
    }
}
