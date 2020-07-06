package com.niluogege.lib.sort;

import java.util.Arrays;

/**
 * 八大排序
 * <p>
 * https://itimetraveler.github.io/2017/07/18/%E5%85%AB%E5%A4%A7%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95%E6%80%BB%E7%BB%93%E4%B8%8Ejava%E5%AE%9E%E7%8E%B0/#%E5%9B%9B%E3%80%81%E5%A0%86%E6%8E%92%E5%BA%8F%EF%BC%88Heap-Sort%EF%BC%89
 */
public class SortTest {
    public static void main(String[] args) {
        int[] arr = createHeapArr();
        System.out.println("原始: " + Arrays.toString(arr));
        heapSort(arr);
    }


    private static int[] createHeapArr() {
        return new int[]{7, 5, 19, 8, 4, 1, 20, 13, 16};
    }

    /**
     * 堆排序
     *
     * @param a
     */
    private static void heapSort(int[] a) {
        for (int i = a.length; i > 0; i--) {

            heapify(a, i);

            int temp = a[0];
            a[0] = a[i - 1];
            a[i - 1] = temp;

            System.out.println("交换: i= " + i + " " + Arrays.toString(a));
        }

        System.out.println("heapSort: " + Arrays.toString(a));

    }

    /**
     * 进行 堆化
     *
     * @param a 数组
     * @param i 索引
     */
    private static void heapify(int[] a, int i) {

        if (a != null && a.length > 0) {

            int parentIndex = i / 2;

            for (; parentIndex >= 0; parentIndex--) {

                if (parentIndex * 2 >= i) continue;

                int leftIndex = parentIndex * 2;
                int rightIndex = (leftIndex + 1) >= i ? leftIndex : leftIndex + 1;


                int maxPos = a[leftIndex] >= a[rightIndex] ? leftIndex : rightIndex;


                //进行交换
                if (a[maxPos] > a[parentIndex]) {
                    int temp = a[parentIndex];
                    a[parentIndex] = a[maxPos];
                    a[maxPos] = temp;
                }
                System.out.println("堆化: i= " + i + " " + Arrays.toString(a));
            }
        }
    }
}
