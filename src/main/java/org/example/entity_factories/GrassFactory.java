package org.example.entity_factories;

import org.example.entities.static_entities.Grass;

import java.util.Properties;

public class GrassFactory extends EntityFactory<Grass> {
    private static final String GRASS_VIEW_PARAM = "grass_view";
    private static final String TIMES_TO_EAT_PARAM = "grass_times_to_eat";
    private static final String ENTITY_NAME = "трава";

    public GrassFactory(Properties config, int numberToSupply){
        super(config, ENTITY_NAME, numberToSupply);
    }

    @Override
    public Grass get() {
        String view = (String) config.get(GRASS_VIEW_PARAM);
        String timesToEat = (String) config.get(TIMES_TO_EAT_PARAM);
        return new Grass(view, Integer.parseInt(timesToEat));
    }
}
