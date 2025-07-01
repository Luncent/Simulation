package org.example.entities.non_living;

import org.example.entities.Entity;
import org.example.entities.InteractiveEntity;

public class Grass extends Entity implements InteractiveEntity {
    private static final int INIT_TIMES_TO_EAT = 3;
    private static final Character GRASS_VIEW = 'G';

    private int timesToEat;

    public Grass(){
        super(GRASS_VIEW);
        timesToEat = INIT_TIMES_TO_EAT;
    }

    //TODO
    @Override
    public void interact() {

    }
}
