package org.example.coordinate_factory;

import lombok.AllArgsConstructor;
import org.example.Coordinate;
import org.example.entities.Entity;

@AllArgsConstructor
public abstract class CoordinateFactory {

    public abstract Coordinate createCoordinate(Entity e);
}
