package org.example.entities.non_living;

import org.example.entities.Entity;
import org.example.entities.InteractiveEntity;

public class Grass extends Entity implements InteractiveEntity {
    private int timesToEat;

    public Grass(Character grassView, int timesToEat){
        super(grassView);
        this.timesToEat = timesToEat;
    }

    //TODO
    @Override
    public void interact() {

    }
}
