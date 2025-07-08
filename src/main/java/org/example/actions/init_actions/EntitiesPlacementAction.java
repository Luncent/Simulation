package org.example.actions.init_actions;

import org.example.Context;
import org.example.GameMap;
import org.example.actions.Action;
import org.example.coordinate_factory.CoordinateFactory;
import org.example.entities.Entity;
import org.example.utils.EntityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.utils.EntityUtil.isStaticObject;

public class EntitiesPlacementAction extends Action {

    private final CoordinateFactory coordinateFactory;
    private final GameMap map;

    public EntitiesPlacementAction(String describingMessage, Context appContext,
                                   CoordinateFactory coordinateFactory) {
        super(describingMessage, appContext);
        this.coordinateFactory = coordinateFactory;
        this.map = appContext.getMap();
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
