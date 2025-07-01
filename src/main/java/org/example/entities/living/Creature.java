package org.example.entities.living;

import org.example.entities.Entity;

public abstract class Creature extends Entity {
    //клетки за ход
    protected final int speed;
    protected final int healthPoints;

    protected Creature(Character creatureView, int speed, int healthPoints){
        super(creatureView);
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

    public abstract void makeMove();
}
