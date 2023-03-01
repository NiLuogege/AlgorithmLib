package com.niluogege.thread;

/**
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 */
public class ThreadTest4 {
    public static void main(String[] args) {

        Thread thread1 =   new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是Thread1 我执行完了");
            }
        });


        Thread thread2 =   new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是Thread2 我执行完了");
            }
        });
        Thread thread3 =   new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是Thread3 我执行完了");
            }
        });

        thread1.start();
        try {
            //等待线程1 执行完
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
        try {
            //等待线程2 执行完
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        thread3.start();
        try {
            //等待线程3 执行完
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
