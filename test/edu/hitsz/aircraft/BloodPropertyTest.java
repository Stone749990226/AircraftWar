package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloodPropertyTest {

    private PropertyFactory propertyFactory;
    private AbstractProperty bloodProperty;
    private HeroAircraft heroAircraft;


    @BeforeEach
    void setUp() {
        propertyFactory = new BloodPropertyFactory();
        bloodProperty = propertyFactory.createProperty(100, 100);
    }

    @DisplayName("Test forward method")
    @Test
    void forward() {
        int locationX1 = bloodProperty.getLocationX();
        int locationY1 = bloodProperty.getLocationY();
        bloodProperty.forward();
        int locationX2 = bloodProperty.getLocationX();
        int locationY2 = bloodProperty.getLocationY();
        assertEquals(locationX2, locationX1);
        assertEquals(locationY2, locationY1 + bloodProperty.getSpeedY());
        for (int i = 0; i < 100; i++) {
            bloodProperty.forward();
        }
        assertTrue(bloodProperty.notValid());
    }

    @DisplayName("Test getProperty method")
    @Test
    void getProperty() {
        heroAircraft = HeroAircraft.getInstance();
        //测试血量是否会加满到超过上限
        bloodProperty.getProperty(heroAircraft);
        assertEquals(Game.HERO_HP, heroAircraft.getHp());
        //测试血量是否上升
        heroAircraft.setHp(50);
        bloodProperty.getProperty(heroAircraft);
        assertEquals(50 + 40, heroAircraft.getHp());
    }
}