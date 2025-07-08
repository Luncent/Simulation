package org.example.entities;

import lombok.*;
import org.example.Coordinate;
import org.example.GameMap;

@Getter
@EqualsAndHashCode
public abstract class Entity {
    protected final String entityView;
    @Setter
    @EqualsAndHashCode.Exclude
    protected GameMap map;
    @Setter
    protected Coordinate coordinate;

    protected Entity(String entityView) {
        this.entityView = entityView;
    }
}
