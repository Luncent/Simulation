package org.example.coordinate_factory;

import org.example.Coordinate;
import org.example.GameMap;
import org.example.entities.Entity;

import java.util.Random;

public class RandomCoordinateFactory extends CoordinateFactory {
    private final GameMap map;
    private final Random random;

    public RandomCoordinateFactory(GameMap map, Random random) {
        this.map = map;
        this.random = random;
    }

    @Override
    public Coordinate createCoordinate(Entity e) {
        return getRandomCoordinate();
    }

    private Coordinate generateUncheckedCoordinate() {
        int row = (int) (random.nextDouble() * (map.getRowsCount()));
        int column = (int) (random.nextDouble() * (map.getColumnsCount()));
        return new Coordinate(row, column);
    }

    private Coordinate getRandomCoordinate() {
        Coordinate coordinate = null;
        do {
            coordinate = generateUncheckedCoordinate();
        } while (!map.coordinateIsEmptyAndExists(coordinate));
        return coordinate;
    }
}
