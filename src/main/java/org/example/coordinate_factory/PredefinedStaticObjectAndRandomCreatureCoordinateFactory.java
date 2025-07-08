package org.example.coordinate_factory;

import com.sun.istack.internal.NotNull;
import org.example.Context;
import org.example.Coordinate;
import org.example.entities.Entity;
import org.example.entities.creatures.Creature;
import org.example.entities.static_entities.Grass;
import org.example.utils.EntityUtil;

import java.util.Map;
import java.util.Queue;

import static org.example.utils.EntityUtil.isStaticObject;

public class PredefinedStaticObjectAndRandomCreatureCoordinateFactory extends CoordinateFactory{
    private final CoordinateFactory randomFactory;
    private final Map<Class, Queue<Coordinate>> staticObjectsCoordinates;

    public PredefinedStaticObjectAndRandomCreatureCoordinateFactory(
            CoordinateFactory randomFactory, Context appContext,
            Map<Class, Queue<Coordinate>> staticObjectsCoordinates) {
        super(appContext);
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
