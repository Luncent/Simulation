package org.example;

import lombok.AllArgsConstructor;
import org.example.entities.Entity;
import org.example.entities.EntitySupplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
    private static final int MAP_PART_FOR_ENTITIES = 4;

    private final int rowsCount;
    private final int columnsCount;
    private final int amountOfEntitiesPerType;
    private final List<EntitySupplier> suppliers;
    private final Map<Coordinate, Entity> entities;

    public GameMap(int rowsCount, int columnsCount, List<EntitySupplier> suppliers){
        this.rowsCount= rowsCount;
        this.columnsCount= columnsCount;
        this.suppliers = suppliers;
        int suppliersAmount = suppliers.size();
        amountOfEntitiesPerType = countAmountOfEachTypeOfEntity(suppliersAmount);
        entities = new HashMap<>(amountOfEntitiesPerType*suppliersAmount);

    }

    private int countAmountOfEachTypeOfEntity(int suppliersAmount){
        return ((rowsCount*columnsCount)/MAP_PART_FOR_ENTITIES)/suppliersAmount;
    }
}
