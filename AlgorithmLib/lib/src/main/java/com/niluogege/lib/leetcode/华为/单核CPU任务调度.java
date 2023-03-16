package com.niluogege.lib.leetcode.华为;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 参考：https://blog.csdn.net/qq2279523723/article/details/128950201
 */
public class 单核CPU任务调度 {


    public static void main(String[] args) {
        //我的方法 只对了一半。。
//        myMethod();

        int[][] tasks = new int[][]{
                {1 ,3, 5 ,2},
                {2 ,1 ,3 ,6},
                {3 ,2 ,5 ,11},
                {4 ,2 ,6 ,12},
                {5 ,3, 3 ,15},
        };

        List<String> res = startTask(tasks);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    private static void myMethod() {
        Scanner in = new Scanner("" +
                "1 3 5 2\n" +
                "2 1 3 6\n" +
                "3 2 5 11\n" +
                "4 2 6 12\n" +
                "5 3 3 15");


        LinkedList<Task> tasks = new LinkedList<Task>();
        //一个优先级队列
        PriorityQueue<Task> queue = new PriorityQueue<Task>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return -(o1.priority - o2.priority);
            }
        });

        //模拟cpu执行
        while (in.hasNextLine()) {
            Task task = Task.str2Task(in.nextLine());
            tasks.add(task);
        }

        int curTime = 0;
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            doTask(queue, curTime, task.inTime - curTime, result);
            queue.add(task);
            curTime = task.inTime;
        }


        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    /**
     * @param curTime    当前时间
     * @param cpuUseTime 可执行CPU任务时间
     * @param queue      待执行CPU任务
     * @param result     执行结果
     */
    private static void doTask(PriorityQueue<Task> queue, int curTime, int cpuUseTime, ArrayList<String> result) {
        System.out.println("curTime="+curTime+" cpuUseTime="+cpuUseTime +" queue="+queue);

        //说明 cpu 没有空闲
        if (cpuUseTime <= 0) {
            return;
        }

        //优先级队列中没有
        if (queue.isEmpty()) {
            return;
        }

        Task firstTask = queue.peek();

        if (cpuUseTime<firstTask.useTime){
            firstTask.useTime = firstTask.useTime - cpuUseTime;
            return;
        }

        firstTask.outTime = curTime + firstTask.useTime;
        result.add(firstTask.id + " " + firstTask.outTime);
        queue.poll();
        doTask(queue, curTime + firstTask.useTime, cpuUseTime - firstTask.useTime, result);
    }

    private static class Task {
        public int id; //id
        public int priority;//优先级
        public int useTime;//使用时间
        public int inTime;//到达时间
        public int outTime = 0;//最终退出时间

        public Task(int id, int priority, int useTime, int inTime) {
            this.id = id;
            this.priority = priority;
            this.useTime = useTime;
            this.inTime = inTime;
        }


        public static Task str2Task(String line) {
            String[] s = line.split(" ");
            return new Task(
                    Integer.parseInt(s[0]),
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3])
            );
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    ", priority=" + priority +
                    ", useTime=" + useTime +
                    ", inTime=" + inTime +
                    '}';
        }
    }


    /**
     * 执行任务
     *
     * @param tasks 待执行任务（按任务到达时间由小到大）
     * @return 任务执行结果
     */
    public static List<String> startTask(int[][] tasks) {
        // 当前时间
        int endTime = 0;
        // 执行结果
        List<String> result = new ArrayList<>();
        // 任务池，key：任务优先级；value：任务链表 ，会自然排序，优先级高的在最下面
        TreeMap<Integer, LinkedList<int[]>> pool = new TreeMap<>();

        for (int[] task : tasks) {
            // 任务到达之前与上一次执行间隙时间，处理该段时间可执行任务
            // task[3] 是 执行时间
            startPoolTask(endTime, task[3] - endTime, pool, result);
            // 将当前任务放至任务池
            pool.putIfAbsent(task[1], new LinkedList<>());
            pool.get(task[1]).add(task);
            // 更新时间为结束时间
            endTime = task[3];
        }

        // 任务添加完毕，顺序执行任务池中任务
        Map.Entry<Integer, LinkedList<int[]>> entry;
        while (!pool.isEmpty()) {
            entry = pool.pollLastEntry();
            for (int[] task : entry.getValue()) {
                endTime += task[2];
                result.add(task[0] + " " + endTime);
            }
        }

        return result;
    }

    /**
     * 执行等待队列中CPU任务
     *
     * @param curTime 当前时间
     * @param time    可执行CPU任务时间
     * @param tasks   待执行CPU任务
     * @param result  执行结果
     */
    private static void startPoolTask(int curTime, int time, TreeMap<Integer, LinkedList<int[]>> tasks, List<String> result) {
        System.out.println("curTime="+curTime+" cpuUseTime="+time +" queue="+tasks);

        // 不存在可执行时间，直接跳过
        if (time <= 0) {
            return;
        }
        // 无等待任务
        if (tasks.isEmpty()) {
            return;
        }
        // 获取优先级最高的任务列表
        Map.Entry<Integer, LinkedList<int[]>> entry = tasks.lastEntry();
        LinkedList<int[]> list = entry.getValue();
        // 取出列表中最先到达的任务
        int[] task = list.getFirst();
        // 任务所需时间大于可执行时间，更新任务所需时间
        if (time < task[2]) {
            task[2] -= time;
            return;
        }
        // 任务执行完成后从队列中移除，并添加执行结果
        list.pop();
        result.add(task[0] + " " + (curTime + task[2]));
        // 若移除后任务列表为空，移除该优先级任务列表
        if (list.isEmpty()) {
            tasks.remove(entry.getKey());
        }
        // 执行下一次CPU空闲任务
        startPoolTask(curTime + task[2], time - task[2], tasks, result);
    }


}
