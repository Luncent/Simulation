package org.example.actions.turn_actions;

import org.example.GameMap;
import org.example.actions.Action;
import org.example.entities.creatures.Creature;

import java.util.Queue;
import java.util.function.Consumer;

import static org.example.utils.CreaturesIterationUtil.resetAndFillCycleBuffer;

public class CycleAction extends Action {
    private static final String DESCRIBING_MESSAGE = "simulate one full cycle";
    private static final int INCREMENT_NUMBER = 1;
    private final Queue<Creature> creaturesCycleBuffer;
    private final Consumer<Integer> increment;
    private final GameMap map;

    public CycleAction(Queue<Creature> creaturesCycleBuffer,
                       Consumer<Integer> increment, GameMap map) {

        super(DESCRIBING_MESSAGE);
        this.creaturesCycleBuffer = creaturesCycleBuffer;
        this.increment = increment;
        this.map = map;
    }

    @Override
    public void execute() {
        resetAndFillCycleBuffer(creaturesCycleBuffer, map);
        while (!creaturesCycleBuffer.isEmpty()) {
            creaturesCycleBuffer.poll().makeMove();
            increment.accept(INCREMENT_NUMBER);
        }
    }
}
