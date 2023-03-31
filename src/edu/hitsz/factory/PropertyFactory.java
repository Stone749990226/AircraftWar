package edu.hitsz.factory;

import edu.hitsz.property.AbstractProperty;

public interface PropertyFactory {
    AbstractProperty createProperty(int locationX, int locationY);
}
