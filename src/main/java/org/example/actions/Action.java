package org.example.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Context;

@Getter
@AllArgsConstructor
public abstract class Action {
    protected final String describingMessage;
    protected final Context applicationContext;

    public abstract void execute();
}
