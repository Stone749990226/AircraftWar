package edu.hitsz.aircraft;

public class BombPropertyFactory implements PropertyFactory {
    @Override
    public AbstractProperty createProperty(int locationX, int locationY) {
        return new BloodProperty(locationX, locationY);
    }
}
