package edu.hitsz.factory;

import edu.hitsz.property.AbstractProperty;
import edu.hitsz.property.BloodProperty;

public class BloodPropertyFactory implements PropertyFactory {
    @Override
    public AbstractProperty createProperty(int locationX, int locationY) {
        return new BloodProperty(locationX, locationY);
    }
}
