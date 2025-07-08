package org.example.entities.creatures;

import lombok.Getter;
import org.example.utils.move_search.EntitiesMovementUtil;

@Getter
public class Predator extends Creature{
    private final int attackDamage;

    public Predator(String view, int health, int speed, int attackDamage, Class targetClazz){
        super(view, speed,health, targetClazz);
        this.attackDamage = attackDamage;
    }

    //TODO
    @Override
    public void makeMove(){
        EntitiesMovementUtil.makeMove(this, targetClazz);
    }
}
