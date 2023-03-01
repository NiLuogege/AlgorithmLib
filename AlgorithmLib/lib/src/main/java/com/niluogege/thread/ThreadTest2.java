package com.niluogege.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 设计四个线程,其中两个线程每次对变量i加1,另外两个线程每次对i减1.
 * <p>
 * 参考：https://blog.csdn.net/yuzongtao/article/details/83378538
 */
public class ThreadTest2 {

    public static void main(String[] args) {
        //使用 AtomicInteger
//        useAtomicInteger();

        //使用
        useSynchronizedWorker();
    }

    //使用 AtomicInteger ，他就是原子性的 内部使用 volatile + cas 实现
    private static void useAtomicInteger() {
        MyWorker worker = new MyWorker();
        worker.threadAdd1();
        worker.threadDelete1();

        //阻塞主线程不让退出
        worker.wait1();
    }

    static class MyWorker {

        AtomicInteger ai = new AtomicInteger();

        public void threadAdd1() {

            for (int i = 0; i < 2; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            add1();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }

        public void threadDelete1() {
            for (int i = 0; i < 2; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            delete1();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }


        private void add1() {
            System.out.println("thradname = " + Thread.currentThread().getName() + "增加之后的值= " + ai.incrementAndGet());
        }

        private void delete1() {
            System.out.println("thradname = " + Thread.currentThread().getName() + "减少之后的值= " + ai.decrementAndGet());
        }

        private synchronized void wait1() {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    //使用 Synchronized
    private static void useSynchronizedWorker() {
        SynchronizedWorker worker = new SynchronizedWorker();
        worker.threadAdd1();
        worker.threadDelete1();

        //阻塞主线程不让退出
        worker.wait1();
    }

    static class SynchronizedWorker {
        private int i = 0;

        public void threadAdd1() {

            for (int i = 0; i < 2; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            add1();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }

        public void threadDelete1() {
            for (int i = 0; i < 2; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            delete1();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }


        /**
         * 因为  add1 和 delete1 共享一个 对象锁 所以
         */


        private synchronized void add1() {
            System.out.println("thradname = " + Thread.currentThread().getName() + "增加之后的值= " + (++i));
        }

        private synchronized void delete1() {
            System.out.println("thradname = " + Thread.currentThread().getName() + "减少之后的值= " + (--i));
        }

        private synchronized void wait1() {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
