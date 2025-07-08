package org.example.utils;

import org.example.GameMap;
import org.example.entities.Entity;
import org.example.entities.creatures.Creature;
import org.example.entities.static_entities.Grass;

import java.util.List;
import java.util.stream.Collectors;

public class EntityUtil {
    public static boolean isStaticObject(Entity entity){
        return !(entity instanceof Creature) && !(entity instanceof Grass);
    }
}
