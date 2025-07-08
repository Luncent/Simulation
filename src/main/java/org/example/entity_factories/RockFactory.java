package org.example.entity_factories;

import org.example.entities.static_entities.Rock;

import java.util.Properties;

public class RockFactory extends EntityFactory<Rock> {
    private static final String VIEW_PARAM = "rock_view";
    private static final String ENTITY_NAME = "камень";

    public RockFactory(Properties config, int numberToSupply) {
        super(config, ENTITY_NAME, numberToSupply);
    }

    @Override
    public Rock get() {
        String view = (String) config.get(VIEW_PARAM);
        return new Rock(view);
    }
}
