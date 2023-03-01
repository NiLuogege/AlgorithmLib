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
         * 加 synchronized 有用的原因是 ，当前使用的是对象锁， add1 和 delete1 这连个方法共享一把锁 就是当前对象 this
         * 也就是说 当一个线程要执行 add1 方法是需要 其他线程释放锁，包括 执行 delete1操作的线程，这样才达到了
         *
         * 不会发生多线程问题的 效果，
         *
         * 总后总结一句， 同一个锁对象会锁住使用这个锁的 所有地方，不管是同一个方法还是其他方法
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
