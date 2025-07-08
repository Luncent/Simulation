package org.example.coordinate_factory;

import lombok.AllArgsConstructor;
import org.example.Context;
import org.example.Coordinate;
import org.example.entities.Entity;

@AllArgsConstructor
public abstract class CoordinateFactory {
    private final Context context;

    public abstract Coordinate createCoordinate(Entity e);
}
