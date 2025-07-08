package org.example;

import lombok.Getter;
import org.example.entities.Entity;
import org.example.entities.creatures.Creature;
import org.example.entity_factories.*;

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
    private List<EntityFactory<? extends Entity>> suppliers;
    private Random random;
    private Scanner scanner;
    private Properties properties;
    private Queue<Creature> creaturesCycleBuffer;
    private Integer stepsMade;

    public Context(){

    }

    public void init() throws IOException {
        properties = loadAppProperties();
        random = new Random();
        scanner = new Scanner(System.in);
        creaturesCycleBuffer = new LinkedList<>();
        stepsMade = STEPS_MADE_INIT;


        map = new GameMap();
        suppliers = initEntitiesSuppliers(properties);
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

    private List<EntityFactory<? extends Entity>> initEntitiesSuppliers(Properties properties){
        List<EntityFactory<? extends Entity>> suppliers = new ArrayList<>();
        suppliers.add(new GrassFactory(properties,3));
        suppliers.add(new HerbivoreFactory(properties,1));
        suppliers.add(new PredatorFactory(properties,1));
        suppliers.add(new RockFactory(properties,6));
        suppliers.add(new TreeFactory(properties,2));
        return suppliers;
    }
}
