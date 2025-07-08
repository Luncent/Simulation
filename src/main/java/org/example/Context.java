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

    private final GameMap map;
    private final List<EntityFactory<? extends Entity>> suppliers;
    private final Random random;
    private final Scanner scanner;
    private final Properties properties;
    private final Queue<Creature> creaturesCycleBuffer;
    private Integer stepsMade;

    public Context() throws IOException {
        this.properties = loadAppProperties();
        this.map = new GameMap();
        this.suppliers = initEntitiesSuppliers(properties);
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.stepsMade = STEPS_MADE_INIT;
        this.creaturesCycleBuffer = new LinkedList<>();
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
        suppliers.add(new RockFactory(properties,2));
        suppliers.add(new TreeFactory(properties,2));
        return suppliers;
    }
}
