package org.example.entities.suppliers;

import org.example.entities.EntitySupplier;
import org.example.entities.living.Herbivore;

import java.util.Properties;

public class HerbivoreSupplier extends EntitySupplier<Herbivore> {
    private static final String VIEW_PARAM = "herbivore_view";
    private static final String HEALTH_PARAM = "herbivore_health";
    private static final String SPEED_PARAM = "herbivore_speed";

    public HerbivoreSupplier(Properties config, int numberToSupply) {
        super(config, numberToSupply);
    }

    @Override
    public Herbivore get() {
        String view = (String) config.get(VIEW_PARAM);
        String health = (String) config.get(HEALTH_PARAM);
        String speed = (String) config.get(SPEED_PARAM);
        return new Herbivore(view.charAt(0), Integer.parseInt(health), Integer.parseInt(speed));
    }
}
