package org.example.utils;

import org.example.Coordinate;
import org.example.GameMap;
import org.example.entities.Entity;
import org.example.entities.InteractiveEntity;
import org.example.entities.creatures.Creature;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.pow;

public class EntitiesMovementUtil {

    public static void makeMove(Creature creature, Class targetClazz){
        IntStream.range(0,creature.getSpeed()).forEach(i->{
            Optional<InteractiveEntity> target = tryToReachTarget(creature);
            if(target.isPresent()){
                target.get().interact(creature);
                creature.getMap().renderMap();
                return;
            }
            moveEntity(creature, targetClazz);
            creature.getMap().renderMap();
        });
    }

    public static Optional<InteractiveEntity> tryToReachTarget(Creature creature){
        Set<Coordinate> coordinates = generateMoves(creature.getCoordinate());
        return coordinates.stream()
                .map(coordinate -> creature.getMap().getEntityByCoordinate(coordinate))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(entity->entity instanceof InteractiveEntity && entity.getClass() == creature.getTargetClazz())
                .map(entity -> (InteractiveEntity)entity)
                .findFirst();
    }

    public static void moveEntity(Creature creature, Class targetClazz){
        Optional<Coordinate> target = findTarget(creature.getCoordinate(), creature.getMap(), targetClazz);
        if(!target.isPresent()){
            return;
        }
        Optional<Coordinate> newCoordinateOptional = findNewCoordinate(creature.getCoordinate(), target.get(), creature.getMap());
        if(!newCoordinateOptional.isPresent()){
            return;
        }
        Coordinate newCoordinate = newCoordinateOptional.get();
        System.out.println(creature.getEntityView()+" moving from "+ creature.getCoordinate() + " to "+ newCoordinate);
        creature.getMap().moveEntity(newCoordinate, creature);
    }

    //TODO need to find out what cell from 4 possible has least iterations to reach target
    private static Optional<Coordinate> findNewCoordinate(Coordinate from, Coordinate target, GameMap map){
        Set<Coordinate> coordinates = generateMoves(from);
        coordinates = coordinates.stream()
                .filter(map::coordinateIsEmptyAndExists)
                .collect(Collectors.toSet());
        double length = coordinates.stream()
                .map(c->calcLength(c, target))
                .sorted()
                .findFirst()
                .get();
        for(Coordinate c: coordinates){
            if(calcLength(c, target)==length){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    private static double calcLength(Coordinate from, Coordinate target){
        return pow(from.getRow()-target.getRow(),2) + pow(from.getColumn()- target.getColumn(),2);
    }

    public static Optional<Coordinate> findTarget(Coordinate entityCoordinate, GameMap map, Class targetClass){
        Set<Coordinate> targetCandidatesQueue = new HashSet<>();
        Set<Coordinate> checkedCoordinates = new HashSet<>();

        targetCandidatesQueue.add(entityCoordinate);
        while(!targetCandidatesQueue.isEmpty()){
            Iterator<Coordinate> iterator = targetCandidatesQueue.iterator();
            Set<Coordinate> childNodes = new HashSet<>();
            while(iterator.hasNext()){
                Coordinate candidate = iterator.next();
                Optional<Entity> optionalEntity = map.getEntityByCoordinate(candidate);
                if(optionalEntity.isPresent() && optionalEntity.get().getClass().equals(targetClass)){
                    return Optional.of(candidate);
                }
                checkedCoordinates.add(candidate);
                childNodes.addAll(getChildNodes(candidate, checkedCoordinates, map, targetClass));
            }
            targetCandidatesQueue.clear();
            targetCandidatesQueue.addAll(childNodes);
        }
        return Optional.empty();
    }

    private static Set<Coordinate> getChildNodes(Coordinate parent, Set<Coordinate> checkedCoordinates,
                                                  GameMap map, Class targetClass){
        Set<Coordinate> childNodes = generateMoves(parent);
        return childNodes.stream()
                .filter(coordinate ->
                        filterObstacleAndBorders(coordinate, map, targetClass, checkedCoordinates)
                ).collect(Collectors.toSet());
    }

    private static Set<Coordinate> generateMoves(Coordinate coordinate){
        Set<Coordinate> childNodes = new HashSet<>();
        childNodes.add(new Coordinate(coordinate.getRow() + 1, coordinate.getColumn()));
        childNodes.add(new Coordinate(coordinate.getRow() - 1, coordinate.getColumn()));
        childNodes.add(new Coordinate(coordinate.getRow(), coordinate.getColumn()-1));
        childNodes.add(new Coordinate(coordinate.getRow(), coordinate.getColumn()+1));
        return childNodes;
    }

    private static boolean filterObstacleAndBorders(Coordinate entityCoordinate, GameMap map,
                                                    Class targetClass, Set<Coordinate> checkedCoordinates){
        boolean coordinateIsEmptyAndNotCheckedYet = map.coordinateIsEmptyAndExists(entityCoordinate)
                && !checkedCoordinates.contains(entityCoordinate);
        Optional<Entity> entity = map.getEntityByCoordinate(entityCoordinate);
        boolean isTarget =  entity.isPresent() && entity.get().getClass()==targetClass;
        return coordinateIsEmptyAndNotCheckedYet || isTarget;
    }

}
