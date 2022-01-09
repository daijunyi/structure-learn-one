package com.daijunyi.structure.sort;

class InsertionSortMain {
    public static void main(String[] args) {
//        SortUtil.smallTest(new InsertionSort());
//        SortUtil.middleTest(new InsertionSort());
//        SortUtil.bigTest(new InsertionSort());

        SortUtil.sort(new InsertionSort(),1000);
        SortUtil.sort(new InsertionSort(),5000);
        SortUtil.sort(new InsertionSort(),10000);
        SortUtil.sort(new InsertionSort(),100000);
        SortUtil.sort(new InsertionSort(),1000000);
        SortUtil.sort(new InsertionSort(),10000000);
        SortUtil.sort(new InsertionSort(),50000000);
        SortUtil.sort(new InsertionSort(),100000000);
    }
}

/**
 * @author djy
 * @createTime 2022/1/7 上午11:47
 * @description 插入排序法
 */
public class InsertionSort implements Sort {

    /**
     * ● 把待排序的数组看成一个有序的表，也看成是一个无序表
     * ● 开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
     * ● 排序过程每次从无序表中取一个元素插入到有序表的合适位置，
     * ● 进行n-1次插入
     * @param source
     * @param <T>
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] source) {

        for (int i = 1; i < source.length; i++) {
            //从前头开始先找到插入位置，然后再从后面右移
//            for (int j = 0;j<i;j++){
//                if (source[i].compareTo(source[j]) < 0){
//                    T tmp = source[i];
//                    for (int k=i;k>j;k--){
//                        source[k] = source[k-1];
//                    }
//                    source[j] = tmp;
//                    break;
//                }
//            }

            //后头开始移动，并且直接找到位置之后，就插入
            T insertValue = source[i];
            int insertIndex = i-1;
            while (insertIndex >= 0 && insertValue.compareTo(source[insertIndex]) < 0){
                source[insertIndex+1] = source[insertIndex];
                insertIndex--;
            }
            if (insertIndex+1 != i){
                source[insertIndex+1] = insertValue;
            }
        }
    }
}
