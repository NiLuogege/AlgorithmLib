package com.niluogege.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量使用
 * 参考：https://blog.csdn.net/eson_15/article/details/51577191
 * <p>
 * 10个线程 3个信号量 相当于 10个人 3个厕所
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        //申请一个信号量，没申请到会阻塞
                        semaphore.acquire();

                        System.out.println(Thread.currentThread().getName()+" 我申请到信号量了，还剩" + semaphore.availablePermits() + "个坑");

                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+" 开始蹲坑");

                        //坑蹲完了 释放一个信号量
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName()+" 坑蹲完了，还剩" + semaphore.availablePermits() + "个坑");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            //执行线程
            executorService.execute(r);
        }

        //关闭线程池
        executorService.shutdown();
    }

}
