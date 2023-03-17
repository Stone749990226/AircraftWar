package edu.hitsz.aircraft;

public class BombProperty extends AbstractProperty{
    public BombProperty(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void getProperty(HeroAircraft heroAircraft) {
        System.out.println("BombSupply active!");
    }
}
