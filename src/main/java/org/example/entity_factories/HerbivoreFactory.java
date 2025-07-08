package org.example.entity_factories;

import org.example.entities.creatures.Herbivore;
import org.example.entities.static_entities.Grass;

import java.util.Properties;

public class HerbivoreFactory extends EntityFactory<Herbivore> {
    private static final String VIEW_PARAM = "herbivore_view";
    private static final String HEALTH_PARAM = "herbivore_health";
    private static final String SPEED_PARAM = "herbivore_speed";
    private static final String ENTITY_NAME = "травоядное";
    private static final Class targetClass = Grass.class;

    public HerbivoreFactory(Properties config, int numberToSupply) {
        super(config, ENTITY_NAME, numberToSupply);
    }

    @Override
    public Herbivore get() {
        String view = (String) config.get(VIEW_PARAM);
        String health = (String) config.get(HEALTH_PARAM);
        String speed = (String) config.get(SPEED_PARAM);
        return new Herbivore(view, Integer.parseInt(health),
                Integer.parseInt(speed),targetClass);
    }
}
