package org.example.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class EntitySupplier <T> implements Supplier<T> {
    protected final Properties config;
    @Getter
    protected final int numberToSupply;
}
