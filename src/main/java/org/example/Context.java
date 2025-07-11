package org.example;

import lombok.Getter;
import org.example.actions.Action;
import org.example.actions.init_actions.EntitiesCreationAction;
import org.example.actions.init_actions.EntitiesPlacementAction;
import org.example.actions.turn_actions.AddEntityAction;
import org.example.actions.turn_actions.CycleAction;
import org.example.coordinate_factory.PredefinedStaticObjectAndRandomCreatureCoordinateFactory;
import org.example.coordinate_factory.RandomCoordinateFactory;
import org.example.entities.Entity;
import org.example.entities.creatures.Creature;
import org.example.entities.static_entities.Rock;
import org.example.entities.static_entities.Tree;
import org.example.entity_factories.*;
import org.example.exceptions.MapCreationException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Getter
public class Context {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.txt";
    private static final int STEPS_MADE_INIT = 0;

    private GameMap map;
    private List<EntityFactory<? extends Entity>> entityFactories;
    private Random random;
    private Scanner scanner;
    private Properties properties;
    private Queue<Creature> creaturesCycleBuffer;
    private Integer stepsMade;

    private GrassFactory grassFactory;
    private TreeFactory treeFactory;
    private RockFactory rockFactory;
    private HerbivoreFactory herbivoreFactory;
    private PredatorFactory predatorFactory;

    private RandomCoordinateFactory randomCoordinateFactory;
    private Map<Class,Queue<Coordinate>> predefinedCoordinates;
    private PredefinedStaticObjectAndRandomCreatureCoordinateFactory predefinedCoordinateFactory;

    private EntitiesCreationAction entitiesCreationAction;
    private EntitiesPlacementAction entitiesPlacementAction;
    private AddEntityAction addEntityAction;
    private CycleAction cycleAction;
    private List<Action> initActions;
    private Map<String, Action> turnActions;

    private Simulation simulation;

    public void init() throws IOException, MapCreationException {
        properties = loadAppProperties();
        random = new Random();
        scanner = new Scanner(System.in);
        creaturesCycleBuffer = new LinkedList<>();
        stepsMade = STEPS_MADE_INIT;

        map = new GameMap(properties, random, creaturesCycleBuffer);
        randomCoordinateFactory = new RandomCoordinateFactory(map, random);
        createPredefinedCoordinateFactory();

        createEntityFactories(properties);
        createInitActions();
        createTurnActions();

        simulation = new Simulation(initActions, turnActions, this::increaseSteps,
                this::getStepsMade, scanner, map, creaturesCycleBuffer);
    }

    //number of static entities should match "numberToSupply" field of its factory
    private void createPredefinedCoordinates(){
        predefinedCoordinates = new HashMap<>();
        Queue<Coordinate> rocksCoordinates = new LinkedList<>();
        rocksCoordinates.add(new Coordinate(5,2));
        rocksCoordinates.add(new Coordinate(6,4));
        rocksCoordinates.add(new Coordinate(8,2));
        rocksCoordinates.add(new Coordinate(6,6));
        rocksCoordinates.add(new Coordinate(6,7));
        rocksCoordinates.add(new Coordinate(7,6));
        rocksCoordinates.add(new Coordinate(7,7));

        predefinedCoordinates.put(Rock.class,rocksCoordinates);

        Queue<Coordinate> treesCoordinates = new LinkedList<>();
        treesCoordinates.add(new Coordinate(2,1));
        treesCoordinates.add(new Coordinate(2,2));
        treesCoordinates.add(new Coordinate(3,2));
        treesCoordinates.add(new Coordinate(4,2));

        treesCoordinates.add(new Coordinate(3,4));
        treesCoordinates.add(new Coordinate(0,6));
        treesCoordinates.add(new Coordinate(1,6));
        treesCoordinates.add(new Coordinate(2,6));

        treesCoordinates.add(new Coordinate(2,8));
        treesCoordinates.add(new Coordinate(2,9));

        treesCoordinates.add(new Coordinate(5,9));
        treesCoordinates.add(new Coordinate(5,6));
        treesCoordinates.add(new Coordinate(8,1));
        treesCoordinates.add(new Coordinate(1,0));

        predefinedCoordinates.put(Tree.class, treesCoordinates);
    }

    private void createEntityFactories(Properties properties){
        grassFactory = new GrassFactory(properties,3);
        rockFactory = new RockFactory(properties,7);
        herbivoreFactory = new HerbivoreFactory(properties,1);
        predatorFactory = new PredatorFactory(properties,1);
        treeFactory = new TreeFactory(properties,13);

        entityFactories = new ArrayList<>();
        entityFactories.add(grassFactory);
        entityFactories.add(rockFactory);
        entityFactories.add(herbivoreFactory);
        entityFactories.add(predatorFactory);
        entityFactories.add(treeFactory);
    }

    private void createInitActions(){
        entitiesCreationAction = new EntitiesCreationAction(entityFactories, map);
        entitiesPlacementAction = new EntitiesPlacementAction(predefinedCoordinateFactory, map);

        initActions = new LinkedList<>();
        initActions.add(entitiesCreationAction);
        initActions.add(entitiesPlacementAction);
    }

    private void createTurnActions(){
        cycleAction = new CycleAction(creaturesCycleBuffer, this::increaseSteps, map);

        turnActions = new TreeMap<>();
        turnActions.put("3",cycleAction);
        turnActions.put("4",new AddEntityAction("grass", creaturesCycleBuffer, randomCoordinateFactory, grassFactory, map));
        turnActions.put("5",new AddEntityAction("cow", creaturesCycleBuffer, randomCoordinateFactory, herbivoreFactory, map));
        turnActions.put("6",new AddEntityAction( "wolf", creaturesCycleBuffer, randomCoordinateFactory, predatorFactory, map));
    }

    private void createPredefinedCoordinateFactory(){
        createPredefinedCoordinates();

        predefinedCoordinateFactory = new PredefinedStaticObjectAndRandomCreatureCoordinateFactory(
                randomCoordinateFactory,predefinedCoordinates);
    }

    public void increaseSteps(int valueToAdd){
        stepsMade+=valueToAdd;
    }

    private Properties loadAppProperties() throws IOException {
        Properties properties = new Properties();
        try (BufferedInputStream bis = new BufferedInputStream(
                Files.newInputStream(Paths.get(CONFIG_FILE_PATH)))
        ) {
            properties.load(bis);
        }
        return properties;
    }

}
