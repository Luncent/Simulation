package org.example;

import lombok.AllArgsConstructor;
import org.example.actions.Action;
import org.example.entities.creatures.Creature;
import java.util.*;

import static org.example.utils.CreaturesIterationUtil.resetAndFillCycleBuffer;
import static org.example.utils.EntityUtil.selectCreatures;
import static org.example.utils.ThreadUtil.startThreads;
import static org.example.utils.ThreadUtil.waitForThreads;

public class Simulation {
    private static final int TIME_BETWEEN_CYCLES_MILL = 1500;

    private final Scanner scanner;
    private final List<Action> initActions;
    private final Map<String,Action> turnActions;
    private final GameMap map;
    private final Context appContext;
    private final Queue<Creature> creaturesCycleBuffer;

    public Simulation(Context appContext,
                      List<Action> initActions,
                      Map<String,Action> turnActions){
        this.appContext = appContext;
        this.scanner = appContext.getScanner();
        this.initActions = initActions;
        this.turnActions = turnActions;
        this.creaturesCycleBuffer = appContext.getCreaturesCycleBuffer();
        this.map = appContext.getMap();
    }

    public void startSimulation() throws InterruptedException {
        executeInitActions();
        while(true) {
            printMenu();
            String option = scanner.nextLine();
            if (option.equals("1")) {
                nextTurn();
            }
            else if(option.equals("2")){
                startEndlessCycle();
            }
            else if(turnActions.containsKey(option)){
                turnActions.get(option).execute();
            }else {
                break;
            }
        }
    }

    public void startEndlessCycle() throws InterruptedException {
        Thread endlessCycleThread = new Thread(new EndlessCycleTask());
        Thread endlessCycleStoppingThread = new Thread(
                new EndlessCycleStoppingTask(endlessCycleThread, scanner)
        );
        startThreads(endlessCycleThread, endlessCycleStoppingThread);
        waitForThreads(endlessCycleThread, endlessCycleStoppingThread);
    }

    public void nextTurn() {
        Creature creatureToMove = nextCreature();
        appContext.increaseSteps(1);
        creatureToMove.makeMove();
    }

    private void executeInitActions(){
        initActions.forEach(Action::execute);
    }

    private class EndlessCycleTask implements Runnable {
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                resetAndFillCycleBuffer(creaturesCycleBuffer, map);
                while(!creaturesCycleBuffer.isEmpty()) {
                    creaturesCycleBuffer.poll().makeMove();
                    appContext.increaseSteps(1);
                    try {
                        Thread.sleep(TIME_BETWEEN_CYCLES_MILL);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Endless cycle interrupted");
                    }
                }
            }
        }
    }

    private Creature nextCreature() {
        if(creaturesCycleBuffer.isEmpty()) {
            resetAndFillCycleBuffer(creaturesCycleBuffer, map);
        }
        return creaturesCycleBuffer.poll();
    }

    @AllArgsConstructor
    private static class EndlessCycleStoppingTask implements Runnable {
        private final Thread endlessCycleThread;
        private final Scanner scanner;
        @Override
        public void run() {
            scanner.nextLine();
            endlessCycleThread.interrupt();
        }
    }

    private void printMenu(){
        System.out.println("(Steps made"+appContext.getStepsMade()+") Choose an option:");
        System.out.println("1 - make move");
        System.out.println("2 - endless cycle (TO END PRESS ANY BUTTON)");
        turnActions.forEach((key,action)-> System.out.println(key+" - "+action.getDescribingMessage()));
        System.out.println("anything else - finish");
    }
}