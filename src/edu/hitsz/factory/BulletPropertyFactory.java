package edu.hitsz.factory;

import edu.hitsz.property.AbstractProperty;
import edu.hitsz.property.BulletProperty;

public class BulletPropertyFactory implements PropertyFactory{
    @Override
    public AbstractProperty createProperty(int locationX, int locationY) {
        return new BulletProperty(locationX, locationY);
    }
}
