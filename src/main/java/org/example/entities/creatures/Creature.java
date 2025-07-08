package org.example.entities.creatures;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.Entity;
import org.example.entities.InteractiveEntity;

public abstract class Creature extends Entity {
    //клетки за ход
    @Getter
    protected final int speed;
    protected int healthPoints;
    @Getter
    protected final Class<? extends InteractiveEntity> targetClazz;

    protected Creature(String creatureView, int speed,
                       int healthPoints, Class<? extends InteractiveEntity> targetClazz){
        super(creatureView);
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.targetClazz = targetClazz;
        //otherwise they can move 2 times in row (1 after spawning, 2 after reset)
    }

    public abstract void makeMove();

}
