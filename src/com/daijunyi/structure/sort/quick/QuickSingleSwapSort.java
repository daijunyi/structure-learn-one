package com.daijunyi.structure.sort.quick;

import com.daijunyi.structure.sort.Sort;
import com.daijunyi.structure.sort.SortUtil;

class QuickSingleSwapSortMain{
    public static void main(String[] args) {
//        SortUtil.smallTest(new QuickSingleSwapSort());
//        SortUtil.middleTest(new QuickSingleSwapSort());
//        SortUtil.bigTest(new QuickSingleSwapSort());

        SortUtil.sort(new QuickSingleSwapSort(),1000);
        SortUtil.sort(new QuickSingleSwapSort(),5000);
        SortUtil.sort(new QuickSingleSwapSort(),10000);
        SortUtil.sort(new QuickSingleSwapSort(),100000);
        SortUtil.sort(new QuickSingleSwapSort(),1000000);
        SortUtil.sort(new QuickSingleSwapSort(),10000000);
        SortUtil.sort(new QuickSingleSwapSort(),50000000);
        SortUtil.sort(new QuickSingleSwapSort(),100000000);
    }
}
/**
 * @author djy
 * @createTime 2022/1/8 下午4:08
 * @description 单指针交换 方式
 * 单指针交换是代码量最少的
 * 这里的单指针意思是定了一个，而动另一个
 * 较少的比较和交换
 */
public class QuickSingleSwapSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        sort(source,0,source.length-1);
    }

    public <T extends Comparable<T>> void sort(T[] source,int l,int r) {
        if(l<r){
            T pivot = source[l];
            int index=l;
            for(int i=l+1;i<=r;i++){
                if(source[i].compareTo(pivot)<0) {
                    ++index;
                    swap(source, i, index);
                }
            }
            swap(source,l,index);
            sort(source,l,index-1);
            sort(source,index+1,r);
        }
    }

    public <T extends Comparable<T>> void swap(T[] source, int left, int right) {
        T tmp = source[left];
        source[left] = source[right];
        source[right] = tmp;
    }
}
