package com.daijunyi.structure.search.impl;

import com.daijunyi.structure.search.Search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author djy
 * @createTime 2022/2/28 下午3:03
 * @description
 */
public class BinarySearch implements Search {
    @Override
    public int indexOf(int[] list, int query) {
        return search(list, query, 0, list.length-1);
    }

    @Override
    public List<Integer> indexOfAll(int[] list, int query) {
        return searchAll(list,query,0,list.length-1);
    }

    /**
     *
     * @param list 查询的列表
     * @param query 需要被查询的值
     * @param left 左边开始角标
     * @param right 右边开始角标
     * @return
     */
    public List<Integer> searchAll(int[] list, int query, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        int result = list[mid];
        if (query > result) {
            return searchAll(list, query, mid + 1, right);
        } else if (query < result) {
            return searchAll(list, query, left, mid - 1);
        } else {
            return getAllByMiddle(list, query, mid);
        }
    }

    /**
     *
     * @param list 查询的列表
     * @param query 需要被查询的值
     * @param left 左边开始角标
     * @param right 右边开始角标
     * @return
     */
    public int search(int[] list, int query, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int result = list[mid];
        if (query > result) {
            return search(list, query, mid + 1, right);
        } else if (query < result) {
            return search(list, query, left, mid - 1);
        } else {
            return mid;
        }
    }
}
