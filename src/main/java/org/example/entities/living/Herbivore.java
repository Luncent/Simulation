package org.example.entities.living;

import org.example.entities.InteractiveEntity;

public class Herbivore extends Creature implements InteractiveEntity {
    private static final Character HERBIVORE_VIEW = 'H';
    private static final int HERBIVORE_DEFAULT_HEALTH = 100;
    private static final int HERBIVORE_DEFAULT_SPEED = 1;

    public Herbivore(){
        super(HERBIVORE_VIEW, HERBIVORE_DEFAULT_SPEED, HERBIVORE_DEFAULT_HEALTH);
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
