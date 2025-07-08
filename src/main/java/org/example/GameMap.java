package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entities.Entity;
import org.example.entities.creatures.Creature;
import org.example.exceptions.MapCreationException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor
@Getter
public class GameMap {
    private static final int MAP_PART_FOR_ENTITIES = 4;
    private static final int MAP_COLUMNS_AND_ROWS_LIMIT = 10;

    private final static String SIZE_EXCEPTION_MSG = "column or row numbers: %s/%s exceed limit %s";
    private final static String ENTITIES_NUMBER_EXCEPTION_MSG = "cant add more entities current number: %s cant exceeds 1/%s of map size (%s)";
    private final static String MAP_CREATED_MSG = "Map created successfully. Max entities number=%s\n";
    private final static String ENTITY_ADDED_MSG = "entity added successfully";
    //for first row to be well formatted
    private Map<Integer, String> emojiNumbers;
    private Random random;
    private int rowsCount;
    private int columnsCount;
    private int entitiesLimit;
    private Map<Coordinate, Entity> entityMap;
    private Queue<Creature> creaturesCycleBuffer;

    private List<Entity> notPlacedEntitiesBuffer;

    public void init(Context context) throws MapCreationException {
        int rowsCount = Integer.parseInt(context.getProperties().getProperty("map_rows"));
        int columnsCount = Integer.parseInt(context.getProperties().getProperty("map_columns"));
        validateMapCreation(columnsCount, rowsCount);
        this.columnsCount = columnsCount;
        this.rowsCount = rowsCount;
        this.entitiesLimit = rowsCount * columnsCount / MAP_PART_FOR_ENTITIES;
        this.emojiNumbers = fillEmojiNumbers();
        this.random = context.getRandom();
        this.creaturesCycleBuffer = context.getCreaturesCycleBuffer();
        entityMap = new HashMap<>(rowsCount * columnsCount);
        notPlacedEntitiesBuffer = new ArrayList<>();
        clearMap();
        //System.out.printf(MAP_CREATED_MSG, entitiesLimit);
    }

    public void renderMap() {
        printColumnsCoordinates();
        renderWorld();
    }

    public void clearMap() {
        IntStream.range(0, rowsCount).forEach(row ->
                IntStream.range(0, columnsCount).forEach(column ->
                        entityMap.put(new Coordinate(row, column), null)
                )
        );
    }

    public boolean coordinateIsEmptyAndExists(Coordinate coordinate) {
        return entityMap.get(coordinate) == null && entityMap.containsKey(coordinate);
    }

    public Optional<Entity> getEntityByCoordinate(Coordinate coordinate) {
        return Optional.ofNullable(entityMap.get(coordinate));
    }

    public void putEntityOnMap(Entity entity, Coordinate coordinate) {
        entityMap.put(coordinate, entity);
        entity.setMap(this);
        entity.setCoordinate(coordinate);
    }

    public void moveEntity(Coordinate newCoordinate, Entity entity) {
        entityMap.put(entity.getCoordinate(), null);
        entity.setCoordinate(newCoordinate);
        entityMap.put(newCoordinate, entity);
    }

    public void removeFromMap(Entity entity) {
        entityMap.put(entity.getCoordinate(), null);
        boolean result = creaturesCycleBuffer.remove(entity);
    }

    public List<Creature> selectCreaturesFromMap(){
        return entityMap.values().stream()
                .filter(entity-> entity instanceof Creature)
                .map(entity -> (Creature)entity)
                .collect(Collectors.toList());
    }

    private void validateMapCreation(int columnsCount, int rowsCount) throws MapCreationException {
        if (columnsCount > MAP_COLUMNS_AND_ROWS_LIMIT || rowsCount > MAP_COLUMNS_AND_ROWS_LIMIT) {
            throw new MapCreationException(String.format(SIZE_EXCEPTION_MSG, columnsCount, rowsCount, MAP_COLUMNS_AND_ROWS_LIMIT));
        }
    }

    private void printColumnsCoordinates() {
        System.out.print(emojiNumbers.get(-1));
        IntStream.range(0, columnsCount).forEach(i ->
                System.out.print(emojiNumbers.get(i))
        );
        System.out.println();
    }

    private void renderWorld() {
        IntStream.range(0, rowsCount).forEach(row -> {
            System.out.print(emojiNumbers.get(row));
            IntStream.range(0, columnsCount).forEach(column -> {
                Coordinate coordinate = new Coordinate(row, column);
                Entity entity = entityMap.get(coordinate);
                String entitySymbol = entity == null ? "⬛" : entity.getEntityView();
                System.out.print(" " + entitySymbol + " ");
            });
            System.out.println();
        });
    }

    private Map<Integer, String> fillEmojiNumbers() {
        Map<Integer, String> emojiNumbers = new HashMap<>(columnsCount);
        emojiNumbers.put(0, " 0️⃣ ");
        emojiNumbers.put(1, " 1️⃣ ");
        emojiNumbers.put(2, " 2️⃣ ");
        emojiNumbers.put(3, " 3️⃣ ");
        emojiNumbers.put(4, " 4️⃣ ");
        emojiNumbers.put(5, " 5️⃣ ");
        emojiNumbers.put(6, " 6️⃣ ");
        emojiNumbers.put(7, " 7️⃣ ");
        emojiNumbers.put(8, " 8️⃣ ");
        emojiNumbers.put(9, " 9️⃣ ");
        emojiNumbers.put(-1, " 🌎 ");
        return emojiNumbers;
    }
}
