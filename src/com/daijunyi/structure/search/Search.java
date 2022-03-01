package com.daijunyi.structure.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author djy
 * @createTime 2022/2/28 下午2:35
 * @description
 */
public interface Search {
    /**
     * 查找其中一个就行
     * @param list
     * @param query
     * @return
     */
    int indexOf(int[] list,int query);

    /**
     * 找到所有
     * @param list
     * @param query
     * @return
     */
    List<Integer> indexOfAll(int[] list, int query);

    /**
     * 根据已经查找的中间值进行查找到所有
     * @param list 查找的列表
     * @param query 需要查找的值
     * @param mid 已经确定的中间值
     * @return
     */
    default ArrayList<Integer> getAllByMiddle(int[] list, int query, int mid) {
        //向左和向右进行扫描
        ArrayList<Integer> integers = new ArrayList<>();
        int index = mid-1;
        while (index >= 0 && index<list.length && list[index] == query){
            integers.add(index);
            index--;
        }
        index = mid+1;
        while (index < list.length && list[index] == query){
            integers.add(index);
            index++;
        }
        integers.add(mid);
        //排序返回
        integers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        return integers;
    }

}
