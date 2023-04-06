package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    private EnemyFactory enemyFactory;
    private AbstractAircraft eliteEnemy;

    @BeforeEach
    void setUp() {
        enemyFactory = new EliteEnemyFactory();
        eliteEnemy = enemyFactory.createEnemy();
    }

    @DisplayName("Test Vanish method")
    @Test
    void Vanish() {
        eliteEnemy.vanish();
        assertTrue(eliteEnemy.notValid());
    }

    @DisplayName("Test Shoot method")
    @Test
    void Shoot() {
        List<BaseBullet> bullets = eliteEnemy.shoot(eliteEnemy);
        //判断射出了子弹
        Assertions.assertNotNull(bullets);
        //判断子弹向前飞行
        for(BaseBullet bullet: bullets){
            Assertions.assertTrue(bullet.getSpeedY() > 0);
        }
    }
}