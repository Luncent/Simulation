package org.example.entities.suppliers;

import org.example.entities.EntitySupplier;
import org.example.entities.non_living.Rock;

import java.util.Properties;

public class RockSupplier extends EntitySupplier<Rock> {
    private static final String VIEW_PARAM = "rock_view";

    public RockSupplier(Properties config, int numberToSupply) {
        super(config, numberToSupply);
    }

    @Override
    public Rock get() {
        String view = (String) config.get(VIEW_PARAM);
        return new Rock(view.charAt(0));
    }
}
