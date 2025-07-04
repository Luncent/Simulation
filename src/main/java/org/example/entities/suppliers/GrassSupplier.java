package org.example.entities.suppliers;

import org.example.entities.EntitySupplier;
import org.example.entities.non_living.Grass;

import java.util.Properties;
import java.util.ResourceBundle;

public class GrassSupplier extends EntitySupplier<Grass> {
    private static final String GRASS_VIEW_PARAM = "grass_view";
    private static final String TIMES_TO_EAT_PARAM = "grass_times_to_eat";

    public GrassSupplier(Properties config, int numberToSupply){
        super(config, numberToSupply);
    }

    @Override
    public Grass get() {
        String view = (String) config.get(GRASS_VIEW_PARAM);
        String timesToEat = (String) config.get(TIMES_TO_EAT_PARAM);
        return new Grass(view.charAt(0), Integer.parseInt(timesToEat));
    }
}
