package org.example.entities;

import java.util.ResourceBundle;
import java.util.function.Supplier;

public abstract class EntitySupplier <T> implements Supplier<T> {
    protected final ResourceBundle config;

    protected EntitySupplier(ResourceBundle config){
        this.config = config;
    }
}
