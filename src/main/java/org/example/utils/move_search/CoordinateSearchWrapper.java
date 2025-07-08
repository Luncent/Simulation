package org.example.utils.move_search;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.Coordinate;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CoordinateSearchWrapper {
    private final Coordinate coordinate;
    @EqualsAndHashCode.Exclude
    private final CoordinateSearchWrapper previousCoordinate;
}
