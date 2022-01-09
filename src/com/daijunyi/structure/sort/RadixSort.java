package com.daijunyi.structure.sort;

import java.util.Arrays;

class RadixSortMain{
    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
        int[] array = {3,9,1,30,20,1,3,2,10,1};
//        Integer[] array = {3,9,-1,10,20};
        System.out.println("排序前："+ Arrays.toString(array));
        radixSort.sort(array);
        System.out.println("排序后："+Arrays.toString(array));

        int size = 100;
        int[] arrayMax = SortUtil.getRandomInt(size);
        System.out.println("开始排序"+size+"个数据");
        System.out.println("排序前："+ Arrays.toString(arrayMax));
        long start = System.currentTimeMillis();
        radixSort.sort(arrayMax);
        long m = (System.currentTimeMillis()-start)/1000;
        System.out.println("排序后："+ Arrays.toString(arrayMax));
        System.out.println("排序时间:"+m+"秒");



        int size1 = 8000000;
        int[] arrayMax1 = SortUtil.getRandomInt(size1);
        System.out.println("开始排序"+size1+"个数据");
        long start1 = System.currentTimeMillis();
        radixSort.sort(arrayMax1);
        long m1 = (System.currentTimeMillis()-start1)/1000;
        System.out.println("排序时间:"+m1+"秒");
    }
}

/**
 * @author djy
 * @createTime 2022/1/9 上午9:48
 * @description 基数排序法
 */
public class RadixSort {

    public void sort(int[] source) {
        if (source == null || source.length < 2){
            return;
        }
        int[][] bucket= new int[10][source.length];
        //每个桶存储index
        int[] bucketElementCounts = new int[10];

        int max = source[0];
        for (int i=1;i<source.length;i++){
            if (source[i] > max){
                max = source[i];
            }
        }
        //最大值有多少位
        int count = (max+"").length();
        for (int i=0,n=1;i<count;i++,n*=10){
            for (int j=0;j<source.length;j++){
                int index = source[j]/n%10;
                bucket[index][bucketElementCounts[index]++] = source[j];
            }
            int index = 0;
            for (int j=0;j<bucket.length;j++){
                for (int k=0;k<bucketElementCounts[j];k++){
                    source[index] = bucket[j][k];
                    index++;
                }
                //赋值完一定要记住把桶中存储记录值置空
                bucketElementCounts[j] = 0;
            }
        }
    }
}
