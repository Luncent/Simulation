package org.example.entities.suppliers;

import org.example.entities.EntitySupplier;
import org.example.entities.non_living.Grass;

import java.util.ResourceBundle;

public class GrassSupplier extends EntitySupplier<Grass> {

    public GrassSupplier(ResourceBundle config){
        super(config);
    }

    //TODO get params from cfg file
    @Override
    public Grass get() {
        return new Grass();
    }
}
