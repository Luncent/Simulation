package org.example.entities.living;

import org.example.entities.InteractiveEntity;

public class Herbivore extends Creature implements InteractiveEntity {

    public Herbivore(Character view, int health, int speed){
        super(view, speed, health);
    }

    //TODO
    @Override
    public void makeMove(){

    }

    //TODO
    @Override
    public void interact() {

    }
}
