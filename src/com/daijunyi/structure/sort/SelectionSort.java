package com.daijunyi.structure.sort;

class SelectionSortMain{
    public static void main(String[] args) {
        SortUtil.smallTest(new SelectionSort());
        SortUtil.middleTest(new SelectionSort());
        SortUtil.bigTest(new SelectionSort());
    }
}

/**
 * @author djy
 * @createTime 2022/1/6 下午5:08
 * @description
 */
public class SelectionSort implements Sort{
    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        if (source == null || source.length <= 1){
            return;
        }
        int index;
        T tmp;
        for (int i=0;i<source.length-1;i++){
            tmp = source[i];
            index = i;
            for (int j=i;j<source.length;j++){
                if (tmp.compareTo(source[j]) > 0){
                    tmp = source[j];
                    index = j;
                }
            }
            if (index != i){
                source[index] = source[i];
                source[i] = tmp;
            }
        }
    }
}
