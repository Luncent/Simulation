package org.example.utils;

import org.example.GameMap;
import org.example.entities.creatures.Creature;

import java.util.Queue;

public class CreaturesIterationUtil {
    public static void resetAndFillCycleBuffer(Queue<Creature> creaturesCycleBuffer, GameMap map){
        creaturesCycleBuffer.clear();
        creaturesCycleBuffer.addAll(map.selectCreaturesFromMap());
        System.out.println("reset cycle of creatures");
    }
}
