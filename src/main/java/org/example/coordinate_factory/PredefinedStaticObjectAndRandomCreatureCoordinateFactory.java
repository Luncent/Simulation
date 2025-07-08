package org.example.coordinate_factory;

import org.example.Coordinate;
import org.example.entities.Entity;

import java.util.Map;
import java.util.Queue;

import static org.example.utils.EntityUtil.isStaticObject;

public class PredefinedStaticObjectAndRandomCreatureCoordinateFactory extends CoordinateFactory{
    private final CoordinateFactory randomFactory;
    private final Map<Class, Queue<Coordinate>> staticObjectsCoordinates;

    public PredefinedStaticObjectAndRandomCreatureCoordinateFactory(
            CoordinateFactory randomFactory, Map<Class, Queue<Coordinate>> staticObjectsCoordinates) {
        this.randomFactory = randomFactory;
        this.staticObjectsCoordinates = staticObjectsCoordinates;
    }

    @Override
    public Coordinate createCoordinate(Entity e) {
        if(isStaticObject(e)) {
            Coordinate coordinate = staticObjectsCoordinates.get(e.getClass()).poll();
            if(coordinate == null) {
                throw new IllegalArgumentException("No coordinate found for " + e.getClass());
            }
            return coordinate;
        }
        return randomFactory.createCoordinate(e);
    }
}
