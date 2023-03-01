package com.niluogege.thread;

/**
 * 编写程序实现,子线程循环3次,接着主线程循环5次,接着再子线程循环3次,主线程循环5次,如此反复,循环3次.
 */
public class ThreadTest1 {

    public static void main(String[] args) {

        //使用 Synchronized 实现
        useSynchronized();

    }


    /**
     * 流程如下
     *
     * useSynchronized() 中的第一层 for循环
     *
     * 1. worker.mainThradMethod() 因为 flag = false ，所以会阻塞主线程
     * 2. worker.subThreadMethod() 因为 flag = false ，随意会继续执行 进行for循环打印语句 然后 设置  flag = true ,并唤醒主线程
     * 3. 主线被唤醒 1 步骤继续执行 进行for循环打印语句 然后 设置  flag = false ,并唤醒子线程
     *
     * 下来浸入 useSynchronized() 中的第一层 for循环
     */
    private static void useSynchronized() {
        Worker worker = new Worker();

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


    static class Worker {
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
}
