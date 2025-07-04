package org.example.entities.suppliers;

import org.example.entities.EntitySupplier;
import org.example.entities.living.Predator;

import java.util.Properties;

public class PredatorSupplier extends EntitySupplier<Predator> {
    private static final String VIEW_PARAM = "predator_view";
    private static final String HEALTH_PARAM = "predator_health";
    private static final String SPEED_PARAM = "predator_speed";
    private static final String DAMAGE_PARAM = "predator_damage";

    public PredatorSupplier(Properties config, int numberToSupply) {
        super(config, numberToSupply);
    }

    @Override
    public Predator get() {
        String view = (String) config.get(VIEW_PARAM);
        String health = (String) config.get(HEALTH_PARAM);
        String speed = (String) config.get(SPEED_PARAM);
        String attackDamage = (String) config.get(DAMAGE_PARAM);
        return new Predator(view.charAt(0), Integer.parseInt(health), Integer.parseInt(speed), Integer.parseInt(attackDamage));
    }
}
