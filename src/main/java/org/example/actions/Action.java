package org.example.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Action {
    protected final String describingMessage;
    //protected final Context applicationContext;

    public abstract void execute();
}
