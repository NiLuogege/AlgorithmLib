package com.niluogege.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写程序实现,子线程循环3次,接着主线程循环5次,接着再子线程循环3次,主线程循环5次,如此反复,循环3次.
 *
 * 参考：https://blog.csdn.net/yuzongtao/article/details/83378538
 */
public class ThreadTest1 {

    public static void main(String[] args) {

        //使用 Synchronized 实现
//        useSynchronized();

        //使用 ReentrantLock 实现
        useReentrantLock();

    }


    /**
     * 使用 Synchronized 配合 wait notify 实现
     * <p>
     * 流程如下
     * <p>
     * useSynchronized() 中的第一层 for循环
     * <p>
     * 1. worker.mainThradMethod() 因为 flag = false ，所以会阻塞主线程
     * 2. worker.subThreadMethod() 因为 flag = false ，随意会继续执行 进行for循环打印语句 然后 设置  flag = true ,并唤醒主线程
     * 3. 主线被唤醒 1 步骤继续执行 进行for循环打印语句 然后 设置  flag = false ,并唤醒子线程
     * <p>
     * 下来浸入 useSynchronized() 中的第一层 for循环
     */
    private static void useSynchronized() {
        SynchronizedWorker worker = new SynchronizedWorker();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    worker.subThreadMethod();
                }
            }
        }).start();

        for (int i = 0; i < 3; i++) {
            worker.mainThradMethod();
        }
    }


    static class SynchronizedWorker {
        //因为是在唤醒其他线程之前 重新赋值的，也就是不存在 并发情况这里可以不添加 volatile 关键字
        private boolean flag = false;

        //主线程循环5次
        public synchronized void mainThradMethod() {
            while (!flag) {

                try {
                    //阻塞住当前线程（主线程） 让 subThreadMethod 限制性
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 5; i++) {
                System.out.println("主线程循环=" + i);
            }

            flag = false;

            //唤醒阻塞在当前对象上的 线程（子线程）
            notify();
        }

        //子线程循环三次
        public synchronized void subThreadMethod() {

            while (flag) {
                try {
                    //阻塞住当前线程（子线程）
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 3; i++) {
                System.out.println("子线程循环=" + i);
            }

            flag = true;
            //唤醒阻塞在当前对象上的 线程（主线程）
            notify();
        }

    }


    /**
     * 使用 ReentrantLock 配合 Condition 实现
     * <p>
     * 代码逻辑和Synchronized一致 ，只不过将 阻塞和唤醒的代码 从 Object的 wait 和 notify  改成了 Condition 的 await 和 signal
     */
    private static void useReentrantLock() {
        ReentrantLockWorker worker = new ReentrantLockWorker();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    worker.subThreadMethod();
                }
            }
        }).start();

        for (int i = 0; i < 3; i++) {
            worker.mainThradMethod();
        }

    }


    static class ReentrantLockWorker {
        private boolean flag = false;
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        //主线程循环5次
        public void mainThradMethod() {

            lock.lock();

            try {

                while (!flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < 5; i++) {
                    System.out.println("主线程循环=" + i);
                }

                flag = false;
                condition.signal();

            } finally {
                lock.unlock();
            }

        }

        //子线程循环三次
        public void subThreadMethod() {

            lock.lock();

            try {

                while (flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < 3; i++) {
                    System.out.println("子线程循环=" + i);
                }

                flag = true;
                condition.signal();


            } finally {
                lock.unlock();
            }

        }
    }
}
