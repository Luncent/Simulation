package org.example;

import org.example.entities.Entity;
import org.example.entities.InteractiveEntity;
import org.example.entities.creatures.Creature;
import org.example.entities.creatures.Herbivore;
import org.example.entities.creatures.Predator;
import org.example.entities.static_entities.Grass;
import org.example.entities.static_entities.Rock;
import org.example.entity_factories.*;
import org.example.exceptions.MapCreationException;
import org.example.utils.EntitiesMovementUtil;

import java.io.IOException;

public class Tests {

    public static void main(String[] args) throws IOException, MapCreationException {
        Creature creature = new Herbivore("d",20,20, Grass.class);
        Creature creature2 = new Predator("d",20,20,20, Grass.class);
        Entity entity = new Grass("sd",20);
        Entity entity2 = new Rock("wed");
        System.out.println(creature instanceof InteractiveEntity);
        System.out.println(creature2 instanceof InteractiveEntity);
        System.out.println(entity instanceof InteractiveEntity);
        System.out.println(entity2 instanceof InteractiveEntity);
    }

}
