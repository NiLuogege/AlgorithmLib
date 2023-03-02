package com.niluogege.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程的三种创建方式
 */
public class CreateTheard {

    public static void main(String[] args) {
        /**
         * 继承Thread 重新run方法
         */
        new MyThread().start();

        /**
         * 实现Runable接口，重写run方法
         */

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("我是 实现Runable接口，重写run方法");
                }
            }
        }).start();

        /**
         * 实现Callable接口，重写run方法
         */

        FutureTask<Integer> ft = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int i = 0;
                for (; i < 10; i++) {
                    System.out.println("我是 实现Callable接口，重写run方法 i=" + i);
                }
                return i;
            }
        });
        new Thread(ft).start();
        try {
            int callableResult = ft.get();
            System.out.println("我是 实现Callable接口，重写run方法 的返回值=" + callableResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 3; i++) {
                System.out.println("我是 继承Thread 重新run方法");
            }
        }
    }

}
