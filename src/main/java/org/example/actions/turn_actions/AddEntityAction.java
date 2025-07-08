package org.example.actions.turn_actions;

import org.example.GameMap;
import org.example.actions.Action;
import org.example.coordinate_factory.CoordinateFactory;
import org.example.entities.Entity;
import org.example.entities.creatures.Creature;
import org.example.entity_factories.EntityFactory;

import java.util.Queue;

public class AddEntityAction extends Action {
    private static final String DESCRIBING_MESSAGE = "add %s on map";
    private final EntityFactory supplier;
    private final CoordinateFactory coordinateFactory;
    private final Queue<Creature> creaturesCycleBuffer;
    private final GameMap map;

    public AddEntityAction(String entityName, Queue<Creature> creaturesCycleBuffer,
                           CoordinateFactory coordinateFactory, EntityFactory entityFactory,
                           GameMap map) {

        super(String.format(DESCRIBING_MESSAGE,entityName));
        supplier = entityFactory;
        this.coordinateFactory = coordinateFactory;
        this.creaturesCycleBuffer = creaturesCycleBuffer;
        this.map = map;
    }

    @Override
    public void execute() {
        Entity entity = supplier.get();
        map.putEntityOnMap(entity,coordinateFactory.createCoordinate(entity));
        if(entity instanceof Creature) {
            creaturesCycleBuffer.add((Creature) entity);
        }
       map.renderMap();
    }
}
