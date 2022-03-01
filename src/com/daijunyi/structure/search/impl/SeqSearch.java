package com.daijunyi.structure.search.impl;

import com.daijunyi.structure.search.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author djy
 * @createTime 2022/2/28 下午2:31
 * @description
 */
public class SeqSearch implements Search {

    @Override
    public int indexOf(int[] list, int query) {
        for (int i=0;i<list.length;i++){
            if (list[i] == query){
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Integer> indexOfAll(int[] list, int query) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i=0;i<list.length;i++){
            if (list[i] == query){
                integers.add(i);
            }
        }
        return null;
    }
}
