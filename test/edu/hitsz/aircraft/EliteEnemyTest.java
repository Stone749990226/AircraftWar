package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @AfterEach
    void tearDown() {
        enemyFactory = null;
        eliteEnemy = null;
    }

    @Test
    void Vanish() {
        eliteEnemy.vanish();
        assertTrue(eliteEnemy.notValid());
    }

    @Test
    void Shoot() {
        List<BaseBullet> bullets = eliteEnemy.shoot();
        //判断射出了子弹
        Assertions.assertNotNull(bullets);
        //判断子弹向前飞行
        for(BaseBullet bullet: bullets){
            Assertions.assertTrue(bullet.getSpeedY() > 0);
        }
    }
}