package com.daijunyi.structure.sort;

class MergeSortMain{
    public static void main(String[] args) {
        SortUtil.smallTest(new MergeSort());
        SortUtil.middleTest(new MergeSort());
        SortUtil.bigTest(new MergeSort());
    }
}

/**
 * @author djy
 * @createTime 2022/1/8 下午5:16
 * @description 归并算法
 */
public class MergeSort implements Sort{

    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        divideAndCombineSort(source,0,source.length-1,new Object[source.length]);
    }

    /**
     * 分和治
     * @param source 原始数组
     * @param left 左边
     * @param right 右边
     * @param tmp 零时对象
     * @param <T>
     */
    public <T extends Comparable<T>> void divideAndCombineSort(T[] source,int left,int right,Object[] tmp){
        if (left < right){
            int middle = (left+right)/2;
            divideAndCombineSort(source,left,middle,tmp);
            divideAndCombineSort(source,middle+1,right,tmp);
            combineSort(source,left,middle,right,tmp);
        }
    }

    /**
     * 合的方法
     * @param source 原始对象
     * @param left 左边
     * @param middle 中间
     * @param right 右边
     * @param tmp
     * @param <T>
     */
    public <T extends Comparable<T>> void combineSort(T[] source,int left,int middle,int right,Object[] tmp){
        int tmpIndex = 0;
        int i = left;
        int j = middle+1;
        while (i<=middle && j<=right){
            if (source[i].compareTo(source[j])<=0){
                tmp[tmpIndex] = source[i];
                tmpIndex++;
                i++;
            }else if(source[i].compareTo(source[j]) > 0){
                tmp[tmpIndex] = source[j];
                tmpIndex++;
                j++;
            }
        }

        //填充剩下的数据
        while (i<=middle){
            tmp[tmpIndex] = source[i];
            tmpIndex++;
            i++;
        }

        while (j <= right){
            tmp[tmpIndex] = source[j];
            tmpIndex++;
            j++;
        }

        int tmpLeft = left;
        tmpIndex = 0;
        while (tmpLeft <= right){
            source[tmpLeft] = (T)tmp[tmpIndex];
            tmpIndex++;
            tmpLeft++;
        }
    }


}
