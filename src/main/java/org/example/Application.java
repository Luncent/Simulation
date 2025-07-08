package org.example;

import org.example.exceptions.MapCreationException;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws InterruptedException, IOException, MapCreationException {
        Context appContext = new Context();
        appContext.init();
        appContext.getSimulation().startSimulation();
    }
}

