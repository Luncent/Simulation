package org.example.entities;

import org.example.entities.creatures.Creature;

public interface InteractiveEntity<T extends Creature, V extends Entity> {
    String TAKING_DAMAGE_MSG = "INTERACTION %s %s EATS %s %s times to eat left=%s";
    String IM_DEAD_MSG = "%s %s ate %s %s";

    void interact(T caller);

    default void interactionResult(V target, T caller, int healthPoints){
        if(healthPoints <= 0){
            System.out.println(String.format(IM_DEAD_MSG, caller.entityView, caller.coordinate, target.entityView, target.coordinate));
            target.map.removeFromMap(target);
            return;
        }
        System.out.println(String.format(TAKING_DAMAGE_MSG,caller.entityView, caller.coordinate,
                target.entityView, target.coordinate, healthPoints));
    }
}
