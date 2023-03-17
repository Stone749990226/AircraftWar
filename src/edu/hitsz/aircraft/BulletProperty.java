package edu.hitsz.aircraft;

public class BulletProperty extends AbstractProperty{

    public BulletProperty(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void getProperty(HeroAircraft heroAircraft) {
        System.out.println("FireSupply active!");
    }
}
