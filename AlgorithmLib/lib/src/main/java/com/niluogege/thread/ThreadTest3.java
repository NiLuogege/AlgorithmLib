package com.niluogege.thread;

public class ThreadTest3 {

    public static void main(String[] args) {
        useSynchronizedWorker();
    }


    //使用 Synchronized
    private static void useSynchronizedWorker() {
        try {
            AddThread add = new AddThread();
            DecThread dec = new DecThread();
            add.start();
            dec.start();
            add.join();
            dec.join();
            System.out.println(Counter.count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Counter {
        public static int count = 0;
    }

    static class AddThread extends Thread {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                Counter.count += 1;
            }
        }
    }

    static class DecThread extends Thread {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                Counter.count -= 1;
            }
        }
    }
}
