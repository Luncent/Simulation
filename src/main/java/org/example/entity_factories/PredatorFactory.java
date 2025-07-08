package org.example.entity_factories;

import org.example.entities.creatures.Herbivore;
import org.example.entities.creatures.Predator;

import java.util.Properties;

public class PredatorFactory extends EntityFactory<Predator> {
    private static final String VIEW_PARAM = "predator_view";
    private static final String HEALTH_PARAM = "predator_health";
    private static final String SPEED_PARAM = "predator_speed";
    private static final String DAMAGE_PARAM = "predator_damage";
    private static final String ENTITY_NAME = "хищник";
    private static final Class targetClass = Herbivore.class;

    public PredatorFactory(Properties config, int numberToSupply) {
        super(config,  ENTITY_NAME, numberToSupply);
    }

    @Override
    public Predator get() {
        String view = (String) config.get(VIEW_PARAM);
        String health = (String) config.get(HEALTH_PARAM);
        String speed = (String) config.get(SPEED_PARAM);
        String attackDamage = (String) config.get(DAMAGE_PARAM);
        return new Predator(view, Integer.parseInt(health),
                Integer.parseInt(speed), Integer.parseInt(attackDamage),targetClass);
    }
}
