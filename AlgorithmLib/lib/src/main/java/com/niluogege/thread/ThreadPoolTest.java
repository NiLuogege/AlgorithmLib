package com.niluogege.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * java 内置线程池
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //用于并发数固定的场景
        ThreadPoolExecutor newFixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        //线程数固定为1 的线程池，用于文件操作，数据库操作等
        ThreadPoolExecutor singleThreadExecutor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();

        //无核心线程，非核心线程无上限，用于瞬间并发很大，但是又有时候没有任务的情况
        ThreadPoolExecutor cachedThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        //核心线程固定，非核心线程无上限，一般用于定时任务
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
    }
}
