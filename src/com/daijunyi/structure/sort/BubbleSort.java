package com.daijunyi.structure.sort;

class BubbleSortMain{
    public static void main(String[] args) {
//        SortUtil.smallTest(new BubbleSort());
//        SortUtil.middleTest(new BubbleSort());
//        SortUtil.bigTest(new BubbleSort());

        SortUtil.sort(new BubbleSort(),1000);
        SortUtil.sort(new BubbleSort(),5000);
        SortUtil.sort(new BubbleSort(),10000);
        SortUtil.sort(new BubbleSort(),100000);
        SortUtil.sort(new BubbleSort(),1000000);
        SortUtil.sort(new BubbleSort(),10000000);
        SortUtil.sort(new BubbleSort(),50000000);
        SortUtil.sort(new BubbleSort(),100000000);
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
