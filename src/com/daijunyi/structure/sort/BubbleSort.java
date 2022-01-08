package com.daijunyi.structure.sort;

class BubbleSortMain{
    public static void main(String[] args) {
        SortUtil.smallTest(new BubbleSort());
        SortUtil.middleTest(new BubbleSort());
        SortUtil.bigTest(new BubbleSort());
    }
}

/**
 * @author djy
 * @createTime 2022/1/6 上午11:41
 * @description 冒泡排序 从低位依次对比大的往后移动
 */
public class BubbleSort implements Sort{

    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        T tmp = null;
        boolean flag = false;
        for (int i=0;i<source.length-1;i++){
            for (int j=0;j<source.length-1-i;j++){
                if(source[j].compareTo(source[j+1]) > 0){
                    tmp = source[j];
                    source[j] = source[j+1];
                    source[j+1] = tmp;
                    flag = true;
                }
            }
            if (flag == false){
                break;
            }
            flag = false;
        }
    }
}
