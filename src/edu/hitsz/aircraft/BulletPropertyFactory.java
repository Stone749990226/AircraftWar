package edu.hitsz.aircraft;

public class BulletPropertyFactory implements PropertyFactory{
    @Override
    public AbstractProperty createProperty(int locationX, int locationY) {
        return new BloodProperty(locationX, locationY);
    }
}
