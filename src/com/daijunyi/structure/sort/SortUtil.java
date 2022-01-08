package com.daijunyi.structure.sort;

import java.util.Arrays;

/**
 * @author djy
 * @createTime 2022/1/6 下午1:37
 * @description 排序工具类
 */
public class SortUtil {

    /**
     * 获取随机数
     * @param size
     * @return
     */
    public static Integer[] getRandomInteger(int size){
        Integer[] integers = new Integer[size];
        for (int i=0;i<size;i++){
            integers[i] = (int)(Math.random()*size);
        }
        return integers;
    }

    /**
     * 排序测试
     * @param sort
     */
    public static void smallTest(Sort sort){
//        Integer[] array = {76, 93, 83, 81, 50, 32, 52, 64, 34, 8, 57, 99, 24, 16, 99, 73, 73, 82};
        Integer[] array = {3,9,-1,30,20,1,3,2,-10,-1};
//        Integer[] array = {3,9,-1,10,20};
        System.out.println("排序前："+ Arrays.toString(array));
        sort.sort(array);
        System.out.println("排序后："+Arrays.toString(array));
    }

    /**
     * 100个数据测试 时间
     * @param sort
     */
    public static void middleTest(Sort sort){
        int size = 100;
        Integer[] arrayMax = SortUtil.getRandomInteger(size);
        System.out.println("开始排序"+size+"个数据");
        System.out.println("排序前："+ Arrays.toString(arrayMax));
        long start = System.currentTimeMillis();
        sort.sort(arrayMax);
        long m = (System.currentTimeMillis()-start)/1000;
        System.out.println("排序后："+ Arrays.toString(arrayMax));
        System.out.println("排序时间:"+m+"秒");
    }

    /**
     * 8万个数据测试 时间
     * @param sort
     */
    public static void bigTest(Sort sort){
        int size = 8000000;
        Integer[] arrayMax = SortUtil.getRandomInteger(size);
        System.out.println("开始排序"+size+"个数据");
        long start = System.currentTimeMillis();
        sort.sort(arrayMax);
        long m = (System.currentTimeMillis()-start)/1000;
        System.out.println("排序时间:"+m+"秒");
    }

}
