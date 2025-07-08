package org.example.utils;

import org.example.GameMap;
import org.example.entities.creatures.Creature;

import java.util.Queue;

import static org.example.utils.EntityUtil.selectCreatures;

public class CreaturesIterationUtil {
    public static void resetAndFillCycleBuffer(Queue<Creature> creaturesCycleBuffer, GameMap map){
        creaturesCycleBuffer.clear();
        creaturesCycleBuffer.addAll(selectCreatures(map));
        System.out.println("reset cycle of creatures");
    }
}
