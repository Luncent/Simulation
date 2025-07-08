package org.example.utils;

import java.util.Arrays;

public class ThreadUtil {
    public static void startThreads(Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }

    public static void waitForThreads(Thread... threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
