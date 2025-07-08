package org.example.entity_factories;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.entities.Entity;

import java.util.Properties;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class EntityFactory<T extends Entity>{
    protected final Properties config;
    @Getter
    private final String entityName;
    @Getter
    protected final int numberToSupply;

    public abstract T get();
}
