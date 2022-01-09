package com.daijunyi.structure.sort.quick;

import com.daijunyi.structure.sort.MergeSort;
import com.daijunyi.structure.sort.Sort;
import com.daijunyi.structure.sort.SortUtil;

class QuickPitOptimizeSortMain {
    public static void main(String[] args) {
//        SortUtil.smallTest(new QuickPitOptimizeSort());
//        SortUtil.middleTest(new QuickPitOptimizeSort());
//        SortUtil.bigTest(new QuickPitOptimizeSort());

        SortUtil.sort(new QuickPitOptimizeSort(),1000);
        SortUtil.sort(new QuickPitOptimizeSort(),5000);
        SortUtil.sort(new QuickPitOptimizeSort(),10000);
        SortUtil.sort(new QuickPitOptimizeSort(),100000);
        SortUtil.sort(new QuickPitOptimizeSort(),1000000);
        SortUtil.sort(new QuickPitOptimizeSort(),10000000);
        SortUtil.sort(new QuickPitOptimizeSort(),50000000);
        SortUtil.sort(new QuickPitOptimizeSort(),100000000);
    }
}

/**
 * @author djy
 * @createTime 2022/1/8 下午4:34
 * @description 填坑优化排序
 */
public class QuickPitOptimizeSort implements Sort {
    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        sort(source, 0, source.length - 1);
    }

    /**
     * 优势是减少一次比较
     * @param source
     * @param l
     * @param r
     * @param <T>
     */
    public <T extends Comparable<T>> void sort(T[] source, int l, int r) {
        if (l < r) {
            int i = l, j = r;
            T pivot = source[l];
            while (i < j) {
                while (i < j && source[j].compareTo(pivot) >= 0) {
                    j--;
                }
                if (i < j) {
                    source[i++] = source[j];
                }
                while (i < j && source[i].compareTo(pivot) <= 0) {
                    i++;
                }
                if (i < j) {
                    source[j--] = source[i];
                }
            }
            source[i] = pivot;
            sort(source, i + 1, r);
            sort(source, l, i - 1);
        }
    }
}
