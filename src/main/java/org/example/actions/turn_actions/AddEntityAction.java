package org.example.actions.turn_actions;

import org.example.Context;
import org.example.GameMap;
import org.example.actions.Action;
import org.example.coordinate_factory.CoordinateFactory;
import org.example.entities.Entity;
import org.example.entities.creatures.Creature;
import org.example.entity_factories.EntityFactory;

public class AddEntityAction extends Action {
    private static final String DESCRIBING_MESSAGE = "add %s on map";
    private final EntityFactory supplier;
    private final CoordinateFactory coordinateFactory;

    public AddEntityAction(Context appContext, Class supplierClazz, String entityName,
                           CoordinateFactory coordinateFactory) {
        super(String.format(DESCRIBING_MESSAGE,entityName), appContext);
        supplier = appContext.getSuppliers().stream()
                .filter(supplier1 -> supplier1.getClass() == supplierClazz)
                .findAny()
                .orElseThrow(() -> new RuntimeException("No supplier found for " + supplierClazz));
        this.coordinateFactory = coordinateFactory;
    }

    @Override
    public void execute() {
        Entity entity = supplier.get();
        applicationContext.getMap().putEntityOnMap(entity,coordinateFactory.createCoordinate(entity));
        if(entity instanceof Creature) {
            applicationContext.getCreaturesCycleBuffer().add((Creature) entity);
        }
        applicationContext.getMap().renderMap();
    }
}
