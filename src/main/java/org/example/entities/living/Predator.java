package org.example.entities.living;

public class Predator extends Creature{
    private int attackDamage;

    public Predator(Character view, int health, int speed, int attackDamage){
        super(view, health, speed);
        this.attackDamage = attackDamage;
    }

    //TODO
    @Override
    public void makeMove(){

    }
}
