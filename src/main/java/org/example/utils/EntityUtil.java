package org.example.utils;

import org.example.GameMap;
import org.example.entities.Entity;
import org.example.entities.creatures.Creature;
import org.example.entities.static_entities.Grass;

import java.util.List;
import java.util.stream.Collectors;

public class EntityUtil {
    public static List<Creature> selectCreatures(GameMap map){
        return map.getEntityMap().values().stream()
                .filter(entity-> entity instanceof Creature)
                .map(entity -> (Creature)entity)
                .collect(Collectors.toList());
    }

    public static boolean isStaticObject(Entity entity){
        return !(entity instanceof Creature) && !(entity instanceof Grass);
    }
}
