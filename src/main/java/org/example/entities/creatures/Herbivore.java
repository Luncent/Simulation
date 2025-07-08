package org.example.entities.creatures;

import org.example.entities.InteractiveEntity;
import org.example.utils.move_search.EntitiesMovementUtil;

public class Herbivore extends Creature implements InteractiveEntity<Predator, Herbivore> {

    public Herbivore(String view, int health,
                     int speed, Class targetClazz){
        super(view, speed, health,targetClazz);
    }

    @Override
    public void makeMove(){
       EntitiesMovementUtil.makeMove(this, targetClazz);
    }

    @Override
    public void interact(Predator caller) {
        healthPoints-=caller.getAttackDamage();
        interactionResult(this, caller, healthPoints);
    }
}
