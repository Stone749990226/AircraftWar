package edu.hitsz.factory;

import edu.hitsz.property.AbstractProperty;
import edu.hitsz.property.BombProperty;

public class BombPropertyFactory implements PropertyFactory {
    @Override
    public AbstractProperty createProperty(int locationX, int locationY) {
        return new BombProperty(locationX, locationY);
    }
}
