package org.example;

import lombok.AllArgsConstructor;
import org.example.entities.Entity;
import org.example.entities.EntitySupplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameMap {
    private static final int MAP_PART_FOR_ENTITIES = 4;

    private final Random random;
    private final int rowsCount;
    private final int columnsCount;
    private final int entitiesLimit;
    private final List<EntitySupplier<? extends Entity>> suppliers;
    private final Map<Coordinate, Entity> entities;

    public GameMap(int rowsCount, int columnsCount, List<EntitySupplier<? extends Entity>> suppliers){
        this.random = new Random();
        this.rowsCount= rowsCount;
        this.columnsCount= columnsCount;
        this.suppliers = suppliers;
        this.entitiesLimit = rowsCount*columnsCount/MAP_PART_FOR_ENTITIES;

        List<Entity> entities = createEntities();

        entities = new HashMap<>(amountOfEntitiesPerType*suppliersAmount);

    }

    private List<Entity> createEntities() {
        return suppliers.stream().map(supplier ->
                        IntStream.range(0, supplier.getNumberToSupply())
                                .mapToObj(i -> supplier.get())
                                .collect(Collectors.toList())
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private Coordinate generateRandomLocation(){
        int row = random.nextDouble()
    }
}
