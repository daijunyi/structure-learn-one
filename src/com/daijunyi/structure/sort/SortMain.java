package com.daijunyi.structure.sort;

import com.daijunyi.structure.sort.quick.QuickSwapSort;

/**
 * @author djy
 * @createTime 2022/1/9 下午3:46
 * @description
 */
public class SortMain {
    public static void main(String[] args) {
        SortUtil.sort(new QuickSwapSort(),1000);
        SortUtil.sort(new QuickSwapSort(),5000);
        SortUtil.sort(new QuickSwapSort(),10000);
        SortUtil.sort(new QuickSwapSort(),100000);
        SortUtil.sort(new QuickSwapSort(),1000000);
        SortUtil.sort(new QuickSwapSort(),10000000);
        SortUtil.sort(new QuickSwapSort(),50000000);
        SortUtil.sort(new QuickSwapSort(),100000000);

        SortUtil.sort(new QuickSwapSort(),1000);
        SortUtil.sort(new QuickSwapSort(),5000);
        SortUtil.sort(new QuickSwapSort(),10000);
        SortUtil.sort(new QuickSwapSort(),100000);
        SortUtil.sort(new QuickSwapSort(),1000000);
        SortUtil.sort(new QuickSwapSort(),10000000);
        SortUtil.sort(new QuickSwapSort(),50000000);
        SortUtil.sort(new QuickSwapSort(),100000000);



        SortUtil.sort(new MergeSort(),1000);
        SortUtil.sort(new MergeSort(),5000);
        SortUtil.sort(new MergeSort(),10000);
        SortUtil.sort(new MergeSort(),100000);
        SortUtil.sort(new MergeSort(),1000000);
        SortUtil.sort(new MergeSort(),10000000);
        SortUtil.sort(new MergeSort(),50000000);
        SortUtil.sort(new MergeSort(),100000000);

        SortUtil.sortInt(1000);
        SortUtil.sortInt(5000);
        SortUtil.sortInt(10000);
        SortUtil.sortInt(100000);
        SortUtil.sortInt(1000000);
        SortUtil.sortInt(10000000);
        SortUtil.sortInt(50000000);
        SortUtil.sortInt(100000000);
    }
}
