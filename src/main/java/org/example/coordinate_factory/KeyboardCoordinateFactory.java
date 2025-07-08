package org.example.coordinate_factory;

import org.example.Context;
import org.example.Coordinate;
import org.example.entities.Entity;

import java.util.Scanner;

public class KeyboardCoordinateFactory extends CoordinateFactory {
    private final Scanner scanner;

    public KeyboardCoordinateFactory(Context appContext) {
        super(appContext);
        this.scanner = appContext.getScanner();
    }

    @Override
    public Coordinate createCoordinate(Entity entity) {
        System.out.println("Enter row: ");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter column: ");
        int y = Integer.parseInt(scanner.nextLine()) ;
        return new Coordinate(x, y);
    }
}
