package com.daijunyi.structure.sort;

/**
 * @author djy
 * @createTime 2022/1/6 上午11:43
 * @description
 */

public interface Sort {

    <T extends Comparable<T>> void sort(T[] source);

}
