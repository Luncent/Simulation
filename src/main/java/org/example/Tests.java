package org.example;

import org.example.exceptions.MapCreationException;
import org.example.utils.move_search.CoordinateSearchWrapper;

import java.io.IOException;

public class Tests {

    public static void main(String[] args) throws IOException, MapCreationException {
        Coordinate coord1 = new Coordinate(1, 2);
        Coordinate coord2 = new Coordinate(1, 2);

        CoordinateSearchWrapper wrapper1 = new CoordinateSearchWrapper(coord1, null);
        CoordinateSearchWrapper wrapper2 = new CoordinateSearchWrapper(coord2, null);

        System.out.println(coord1.equals(wrapper1));
    }

}
