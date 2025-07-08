package org.example.utils.move_search;

import org.example.Coordinate;
import org.example.entities.InteractiveEntity;
import org.example.entities.creatures.Creature;

import java.util.*;
import java.util.stream.IntStream;

import static org.example.utils.move_search.SearchingUtil.findTargetBFSAlgorithm;
import static org.example.utils.move_search.SearchingUtil.generate4NotCheckedDirectionalMoves;

public class EntitiesMovementUtil {

    public static void makeMove(Creature creature, Class targetClazz){
        IntStream.range(0,creature.getSpeed()).forEach(i->{

            Optional<InteractiveEntity> target = tryToReachTarget(creature);
            if(target.isPresent()){
                target.get().interact(creature);
                creature.getMap().renderMap();
                return;
            }

            moveEntityToTarget(creature, targetClazz);
            creature.getMap().renderMap();
        });
    }

    //gets target if its in range
    public static Optional<InteractiveEntity> tryToReachTarget(Creature creature){
        Set<Coordinate> coordinates = generate4NotCheckedDirectionalMoves(creature.getCoordinate());
        return coordinates.stream()
                .map(coordinate -> creature.getMap().getEntityByCoordinate(coordinate))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(entity->entity instanceof InteractiveEntity && entity.getClass() == creature.getTargetClazz())
                .map(entity -> (InteractiveEntity)entity)
                .findFirst();
    }

    public static void moveEntityToTarget(Creature creature, Class targetClazz){
        Optional<CoordinateSearchWrapper> target = findTargetBFSAlgorithm(creature.getCoordinate(), creature.getMap(), targetClazz);
        //if not present -> either target is missing || creature cant move
        if(!target.isPresent()){
            return;
        }
        Coordinate newCoordinate = findNewCoordinate(target.get());
        System.out.println(creature.getEntityView()+" moving from "+ creature.getCoordinate() + " to "+ newCoordinate);
        creature.getMap().moveEntity(newCoordinate, creature);
    }

    //recursion to find from WHAT CELL NEAREST NOT BLOCKED WAY TO TARGET was found
    private static Coordinate findNewCoordinate(CoordinateSearchWrapper target){
        CoordinateSearchWrapper previousWrapper = target.getPreviousCoordinate();
        if(previousWrapper == null){
            return target.getCoordinate();
        }
        return findNewCoordinate(previousWrapper);
    }
}
