package org.example.actions.init_actions;


import org.example.Context;
import org.example.GameMap;
import org.example.actions.Action;
import org.example.entities.Entity;
import org.example.entity_factories.EntityFactory;

import java.util.List;
import java.util.stream.IntStream;

public class EntitiesCreationAction extends Action {
    private static final String DESCRIBING_MESSAGE = "Create entities";
    private final List<EntityFactory<? extends Entity>> factories;
    private final GameMap map;

    public EntitiesCreationAction(Context appContext) {
        super(DESCRIBING_MESSAGE, appContext);
        this.factories = appContext.getSuppliers();
        this.map = appContext.getMap();
    }

    @Override
    public void execute() {
        List<Entity> entitiesBuffer = map.getNotPlacedEntitiesBuffer();
        factories.stream().forEach(factory ->
                IntStream.range(0, factory.getNumberToSupply())
                        .forEach(i -> entitiesBuffer.add(factory.get())
                ));
    }
}
