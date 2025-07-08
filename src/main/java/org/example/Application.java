package org.example;

import org.example.actions.Action;
import org.example.actions.init_actions.EntitiesCreationAction;
import org.example.actions.init_actions.EntitiesPlacementAction;
import org.example.actions.turn_actions.AddEntityAction;
import org.example.actions.turn_actions.CycleAction;
import org.example.coordinate_factory.CoordinateFactory;
import org.example.coordinate_factory.KeyboardCoordinateFactory;
import org.example.coordinate_factory.PredefinedStaticObjectAndRandomCreatureCoordinateFactory;
import org.example.coordinate_factory.RandomCoordinateFactory;
import org.example.entities.static_entities.Rock;
import org.example.entities.static_entities.Tree;
import org.example.entity_factories.*;
import org.example.exceptions.MapCreationException;

import java.io.IOException;
import java.util.*;

public class Application {

    public static void main(String[] args) throws InterruptedException, IOException, MapCreationException {
        //some setting here
        Context appContext = new Context();
        appContext.getMap().init(appContext);

        Map<Class,Queue<Coordinate>> predefinedCoordinates = new HashMap<>();
        Queue<Coordinate> coordinatesR = new LinkedList<>();
        coordinatesR.add(new Coordinate(0,0));
        coordinatesR.add(new Coordinate(0,1));
        predefinedCoordinates.put(Rock.class,coordinatesR);

        Queue<Coordinate> coordinatesT = new LinkedList<>();
        coordinatesT.add(new Coordinate(1,0));
        coordinatesT.add(new Coordinate(1,1));
        predefinedCoordinates.put(Tree.class, coordinatesT);

        CoordinateFactory randomCoordinateFactory = new RandomCoordinateFactory(appContext);
        CoordinateFactory predefinedCoordinateFactory = new PredefinedStaticObjectAndRandomCreatureCoordinateFactory(
                randomCoordinateFactory,appContext,predefinedCoordinates);

        List<Action> initActions = new LinkedList<>();
        initActions.add(
                //creates entities
                new EntitiesCreationAction(appContext)
        );
        initActions.add(
                //places created entities
                new EntitiesPlacementAction("Place/replace entities randomly", appContext, predefinedCoordinateFactory)
        );

        Map<String, Action> turnActions = new TreeMap<>();
        turnActions.put("3",new CycleAction(appContext));
        turnActions.put("4",new AddEntityAction(appContext, GrassFactory.class, "grass", randomCoordinateFactory));
        turnActions.put("5",new AddEntityAction(appContext, HerbivoreFactory.class, "cow", randomCoordinateFactory));
        turnActions.put("6",new AddEntityAction(appContext, PredatorFactory.class, "wolf", randomCoordinateFactory));

        //configures map settings
        //creates entities
        Simulation simulation = new Simulation(appContext, initActions, turnActions);

        simulation.startSimulation();
    }
}

