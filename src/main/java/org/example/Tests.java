package org.example;

import org.example.entities.Entity;
import org.example.entities.EntitySupplier;
import org.example.entities.non_living.Grass;
import org.example.entities.suppliers.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tests {
    public static Scanner scanner = new Scanner(System.in);

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        try(BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(Paths.get("src/main/resources/config.txt")))) {
            properties.load(bis);
        }
        List<EntitySupplier<? extends Entity>> suppliers = new ArrayList<>(5);
        suppliers.add(new GrassSupplier(properties, 3));
        suppliers.add(new HerbivoreSupplier(properties, 3));
        suppliers.add(new PredatorSupplier(properties, 3));
        suppliers.add(new RockSupplier(properties, 3));
        suppliers.add(new TreeSupplier(properties, 3));
        List<Entity> entities = suppliers.stream().map(EntitySupplier::get).collect(Collectors.toList());

        System.out.println("asd");
    }

    /*public static void main(String[] args) {
        Thread game = new Thread(Tests::gameTask);
        Thread endGame = new Thread(()->endGame(game));

        startThreads(game, endGame);
    }*/

    public static void startThreads(Thread... threads){
        Arrays.stream(threads).forEach(Thread::start);
    }

    private static void endGame(Thread threadToStop){
        String input = scanner.nextLine();
        if(input.equals("e")){
            System.out.println("Interrupting game");
            threadToStop.interrupt();
        }
        else {
            System.out.println("Interrupting game but not like intended");
            threadToStop.interrupt();
        }
    }

    public static void render(){
        clear();
        System.out.println("This will be cleared.");
        System.out.println("To enter: e");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            //throw new RuntimeException(e);
        }
    }

    private static void gameTask(){
        while(!Thread.currentThread().isInterrupted()){
            render();
        }
    }
}
