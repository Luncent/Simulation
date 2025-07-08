package org.example.entities.static_entities;

import org.example.entities.Entity;
import org.example.entities.InteractiveEntity;
import org.example.entities.creatures.Herbivore;

public class Grass extends Entity implements InteractiveEntity<Herbivore, Grass> {

    private int timesToEat;

    public Grass(String grassView, int timesToEat){
        super(grassView);
        this.timesToEat = timesToEat;
    }

    @Override
    public void interact(Herbivore caller) {
        timesToEat--;
        interactionResult(this, caller, timesToEat);
    }
}
