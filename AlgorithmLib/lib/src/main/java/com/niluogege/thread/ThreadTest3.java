package com.niluogege.thread;

import java.util.LinkedList;

/**
 * 经典生产者和消费者问题
 * <p>
 * https://blog.csdn.net/ldx19980108/article/details/81707751
 */
public class ThreadTest3 {

    public static void main(String[] args) {

        //共享一个仓库
        try {
            Storage storage = new Storage();

            Thread producer1 = new Thread(new Producer(storage));
            Thread producer2 = new Thread(new Producer(storage));
            Thread producer3 = new Thread(new Producer(storage));

            Thread consumer1 = new Thread(new Consumer(storage));
            Thread consumer2 = new Thread(new Consumer(storage));
            Thread consumer3 = new Thread(new Consumer(storage));


            producer1.start();
            producer2.start();
            producer3.start();

            consumer1.start();
            consumer2.start();
            consumer3.start();

            producer1.join();
            producer2.join();
            producer3.join();

            consumer1.join();
            consumer2.join();
            consumer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 面向对象思想，仓库具有，库存 ，生产，消费的 功能
     */
    static class Storage {
        //最大库存
        private static final int MAX_SIZE = 10;

        private LinkedList<Object> list = new LinkedList<>();

        //生产方法
        public void produce() {

            synchronized (list) {

                //库存满了 暂定生产,等待消费者消费
                while (list.size() >= MAX_SIZE) {
                    System.out.println("thread=" + Thread.currentThread().getName() + "库存满了 ，暂停生产");
                    try {
                        //阻塞当前线程，到这里如果被唤醒会继续从这里往下执行，所以如果这里是if 那么就会继续向下走，去生产
                        //这样就有可能出现 超库存的情况，所以这里要改成while循环
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                list.add(new Object());
                System.out.println("thread=" + Thread.currentThread().getName() + "生产了一个 当前有库存=" + list.size());

                //唤醒所有线程 ，该生产生产（满了就阻塞），该消费消费（没了就阻塞）
                list.notifyAll();
            }
        }

        //消费方法
        public void consume() {

            synchronized (list) {

                //没了就阻塞当前线程，等待生产者生产
                while (list.size() <= 0) {
                    System.out.println("thread=" + Thread.currentThread().getName() + "库存没了 ，暂停消费");
                    try {
                        //阻塞当前线程，到这里如果被唤醒会继续从这里往下执行，所以如果这里是if 那么就会继续向下走，去消费
                        //这样就有可能出现 库存为负数的情况，所以这里要改成while循环
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                list.removeFirst();
                System.out.println("thread=" + Thread.currentThread().getName() + "消费了一个 当前有库存=" + list.size());

                //唤醒所有线程 ，该生产生产（满了就阻塞），该消费消费（没了就阻塞）
                list.notifyAll();
            }
        }
    }


    //生产者
    static class Producer implements Runnable {
        Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while (true) {

                storage.produce();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //消费者
    static class Consumer implements Runnable {
        Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while (true) {
                storage.consume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
