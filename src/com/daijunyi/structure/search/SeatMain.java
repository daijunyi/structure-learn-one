package com.daijunyi.structure.search;

import com.daijunyi.structure.search.impl.FibonacciSearch;

import java.util.List;

/**
 * @author djy
 * @createTime 2022/2/28 下午2:48
 * @description
 */
public class SeatMain {

    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 5, 6, 9, 10, 28, 100, 100, 100, 101, 103};
//        Search search = new SeqSearch();
//        Search search = new BinarySearch();
//        Search search = new InterpolationSearch();
        Search search = new FibonacciSearch();
        int result = search.indexOf(list, 3);
        System.out.println("结果:" + result);
        List<Integer> integers = search.indexOfAll(list, 103);
        for (Integer item : integers) {
            System.out.println("多个结果查找:" + item);
        }

    }

}
