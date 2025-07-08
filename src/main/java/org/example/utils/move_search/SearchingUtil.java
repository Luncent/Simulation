package org.example.utils.move_search;

import org.example.Coordinate;
import org.example.GameMap;
import org.example.entities.Entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchingUtil {

    public static Optional<CoordinateSearchWrapper> findTargetBFSAlgorithm(Coordinate entityCoordinate, GameMap map, Class targetClass){
        Set<CoordinateSearchWrapper> targetCandidatesQueue = new HashSet<>();
        Set<CoordinateSearchWrapper> checkedCoordinates = new HashSet<>();
        //we start searching from surrounding cells so just mark current pos as checked
        CoordinateSearchWrapper current = new CoordinateSearchWrapper(entityCoordinate, null);
        targetCandidatesQueue.add(current);
        //setting surrounding cells prev cells to null. It will be the condition for recursion end on one of SURROUNDING cells
        targetCandidatesQueue.addAll(getChildNodes(current,checkedCoordinates, map, targetClass, true));
        while(!targetCandidatesQueue.isEmpty()){
            Iterator<CoordinateSearchWrapper> iterator = targetCandidatesQueue.iterator();
            Set<CoordinateSearchWrapper> childNodes = new HashSet<>();
            while(iterator.hasNext()){
                CoordinateSearchWrapper candidate = iterator.next();
                Optional<Entity> optionalEntity = map.getEntityByCoordinate(candidate.getCoordinate());
                if(optionalEntity.isPresent() && optionalEntity.get().getClass().equals(targetClass)){
                    return Optional.of(candidate);
                }
                checkedCoordinates.add(candidate);
                childNodes.addAll(getChildNodes(new CoordinateSearchWrapper(candidate.getCoordinate(), candidate), checkedCoordinates, map, targetClass, false));
            }
            targetCandidatesQueue.clear();
            targetCandidatesQueue.addAll(childNodes);
        }
        return Optional.empty();
    }

    private static Set<CoordinateSearchWrapper> getChildNodes(CoordinateSearchWrapper parent, Set<CoordinateSearchWrapper> checkedCoordinates,
                                                              GameMap map, Class targetClass, boolean isFirstGeneration){
        Set<Coordinate> childNodes = generate4NotCheckedDirectionalMoves(parent.getCoordinate());
        return childNodes.stream()
                .map(coordinate -> new CoordinateSearchWrapper(coordinate, isFirstGeneration ? null : parent))
                .filter(searchWrapper ->
                        filterObstacleAndBordersAndCheckedCells(searchWrapper, map, targetClass, checkedCoordinates)
                )
                .collect(Collectors.toSet());
    }

    public static Set<Coordinate> generate4NotCheckedDirectionalMoves(Coordinate coordinate){
        Set<Coordinate> notCheckedDirectionalMoves = new HashSet<>();
        notCheckedDirectionalMoves.add(new Coordinate(coordinate.getRow() + 1, coordinate.getColumn()));
        notCheckedDirectionalMoves.add(new Coordinate(coordinate.getRow() - 1, coordinate.getColumn()));
        notCheckedDirectionalMoves.add(new Coordinate(coordinate.getRow(), coordinate.getColumn()-1));
        notCheckedDirectionalMoves.add(new Coordinate(coordinate.getRow(), coordinate.getColumn()+1));
        return notCheckedDirectionalMoves;
    }

    private static boolean filterObstacleAndBordersAndCheckedCells(CoordinateSearchWrapper coordinateWrapper, GameMap map,
                                                                   Class targetClass, Set<CoordinateSearchWrapper> checkedCoordinates){
        boolean coordinateIsEmptyAndNotCheckedYet = map.coordinateIsEmptyAndExists(coordinateWrapper.getCoordinate())
                && !checkedCoordinates.contains(coordinateWrapper);
        Optional<Entity> entity = map.getEntityByCoordinate(coordinateWrapper.getCoordinate());
        boolean isTarget =  entity.isPresent() && entity.get().getClass()==targetClass;
        return coordinateIsEmptyAndNotCheckedYet || isTarget;
    }

}
