package org.example.actions.turn_actions;

import org.example.Context;
import org.example.actions.Action;
import org.example.entities.creatures.Creature;

import java.util.Queue;

import static org.example.utils.CreaturesIterationUtil.resetAndFillCycleBuffer;

public class CycleAction extends Action {
    private static final String DESCRIBING_MESSAGE = "simulate one full cycle";
    private static final int INCREMENT_NUMBER = 1;

    public CycleAction(Context appContext) {
        super(DESCRIBING_MESSAGE, appContext);
    }

    @Override
    public void execute() {
        Queue<Creature> creaturesCycleBuffer = applicationContext.getCreaturesCycleBuffer();
        resetAndFillCycleBuffer(creaturesCycleBuffer, applicationContext.getMap());
        while (!creaturesCycleBuffer.isEmpty()) {
            creaturesCycleBuffer.poll().makeMove();
            applicationContext.increaseSteps(INCREMENT_NUMBER);
        }
    }
}
