package com.niluogege.thread;

/**
 * 死锁示例
 * 参考： https://juejin.cn/post/6844903520886325255
 * <p>
 * 思路： 发生死锁的原因是所的竞争，说的详细点儿就是 A 线程想获得B锁 但是被 B线程持有了， B线程想获得A锁，但是被 A线程持有了
 */
public class DeadLockTest {
    static  Object lock1 = new Object();
    static  Object lock2 = new Object();


    public static void main(String[] args) {
        new Thread(new T1(lock1,lock2)).start();
        new Thread(new T2(lock1,lock2)).start();

    }


    /**
     * 线程1 先要获取到 lock1 再要获取 lock2
     */
    static class T1 implements Runnable {
        Object lock1;
        Object lock2;

        public T1(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }


        @Override
        public void run() {
            //先获取 lock1
            synchronized (lock1) {

                System.out.println("T1 获得到了 lock1");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                //获取 lock2
                synchronized (lock2) {
                    System.out.println("T1 获得到了 lock2");
                }
            }
        }
    }


    /**
     * 线程2 先要获取到 lock2 再要获取  lock1
     */
    static class T2 implements Runnable {
        Object lock1;
        Object lock2;

        public T2(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }


        @Override
        public void run() {
            //先获取 lock2
            synchronized (lock2) {

                System.out.println("T2 获得到了 lock2");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                //获取 lock1
                synchronized (lock1) {
                    System.out.println("T2 获得到了 lock1");
                }
            }
        }
    }

}
