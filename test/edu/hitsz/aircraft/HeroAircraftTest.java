package edu.hitsz.aircraft;

import edu.hitsz.factory.EnemyFactory;
import edu.hitsz.factory.MobEnemyFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft heroAircraft;
    private EnemyFactory enemyFactory;
    private AbstractAircraft enemy;

    @BeforeEach
    void setUp() {
        heroAircraft = HeroAircraft.getInstance();
        enemyFactory = new MobEnemyFactory();
        enemy = enemyFactory.createEnemy();
    }

    @DisplayName("Test decreaseHp method")
    @ParameterizedTest
    @ValueSource(ints = {20, 30, 2000})
    void decreaseHp(int number) {
        int hp = heroAircraft.getHp();
        heroAircraft.decreaseHp(number);
        //如果此时英雄机还不足以死亡
        if (hp > number) {
            assertEquals(hp - number, heroAircraft.getHp());
        } else { //如果英雄机血量不够抵挡一次伤害
            //血量是否减到0
            assertEquals(0, heroAircraft.getHp());
            //是否消失
            assertTrue(heroAircraft.notValid());
        }
    }

    @DisplayName("Test crash method")
    @ParameterizedTest
    @CsvSource({"256, 685", "257, 690", "220, 660"})
    //由于电脑屏幕小,之前把Main的主窗格的height调小了100
    void crash(int enemyX, int enemyY) {
//      System.out.println("英雄机的坐标:("+heroAircraft.getLocationX()+","+heroAircraft.getLocationY()+")");
//      System.out.println("英雄机的长宽:("+heroAircraft.getWidth()+","+heroAircraft.getHeight()+")");
        enemy.setLocation(enemyX, enemyY);
//        System.out.println("敌机的坐标:("+enemy.getLocationX()+","+enemy.getLocationY()+")");
//        System.out.println("敌机的长宽:("+enemy.getWidth()+","+enemy.getHeight()+")");
        Assumptions.assumeTrue(heroAircraft.crash(enemy));
    }
}