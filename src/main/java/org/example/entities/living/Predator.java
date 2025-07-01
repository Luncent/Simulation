package org.example.entities.living;

public class Predator extends Creature{
    private static final Character PREDATOR_VIEW = 'P';
    private static final int PREDATOR_DEFAULT_HEALTH = 100;
    private static final int PREDATOR_DEFAULT_SPEED = 2;
    private static final int PREDATOR_DEFAULT_ATTACK_DAMAGE = 2;


    private int attackDamage;

    public Predator(){
        super(PREDATOR_VIEW, PREDATOR_DEFAULT_HEALTH, PREDATOR_DEFAULT_SPEED);
        attackDamage = PREDATOR_DEFAULT_ATTACK_DAMAGE;
    }

    //TODO
    @Override
    public void makeMove(){

    }
}
