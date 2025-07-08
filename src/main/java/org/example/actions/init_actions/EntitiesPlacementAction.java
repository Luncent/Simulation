package org.example.actions.init_actions;

import org.example.GameMap;
import org.example.actions.Action;
import org.example.coordinate_factory.CoordinateFactory;
import org.example.entities.Entity;
import org.example.utils.EntityUtil;

import java.util.List;

import static org.example.utils.EntityUtil.isStaticObject;

public class EntitiesPlacementAction extends Action {
    private static final String DESCRIBING_MESSAGE = "Place/replace entities randomly";
    private final CoordinateFactory coordinateFactory;
    private final GameMap map;

    public EntitiesPlacementAction(CoordinateFactory coordinateFactory,
                                   GameMap map) {
        super(DESCRIBING_MESSAGE);
        this.coordinateFactory = coordinateFactory;
        this.map = map;
    }

    @Override
    public void execute() {
        //получить сущности
        // и для каждой собрать корды
        //put на карту
        map.clearMap();
        List<Entity> entitiesBuffer = map.getNotPlacedEntitiesBuffer();
        placeStaticThenMoving(entitiesBuffer);
        //clear buffer
        entitiesBuffer.clear();
        map.renderMap();
    }

    private void placeStaticThenMoving(List<Entity> entitiesBuffer){
        entitiesBuffer.stream().filter(EntityUtil::isStaticObject)
                .forEach(entity ->{
            System.out.println("placing "+entity.getEntityView());
            map.putEntityOnMap(entity, coordinateFactory.createCoordinate(entity));
        });
        entitiesBuffer.stream().filter(e->!isStaticObject(e))
                .forEach(entity ->{
                    System.out.println("placing "+entity.getEntityView());
                    map.putEntityOnMap(entity, coordinateFactory.createCoordinate(entity));
                });
    }

}
