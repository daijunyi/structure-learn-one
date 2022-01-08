package com.daijunyi.structure.sort.quick;

        import com.daijunyi.structure.sort.Sort;
        import com.daijunyi.structure.sort.SortUtil;

class QuickSwapSortMain {
    public static void main(String[] args) {
        SortUtil.smallTest(new QuickSwapSort());
        SortUtil.middleTest(new QuickSwapSort());
        SortUtil.bigTest(new QuickSwapSort());
    }
}

/**
 * @author djy
 * @createTime 2022/1/8 下午2:51
 * @description 快速排序交换方式
 */
public class QuickSwapSort implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        sort(source, 0, source.length - 1);
    }

    public <T extends Comparable<T>> void sort(T[] source, int left, int right) {
        if (left < right){
            T pivot = source[(left + right) / 2];
            int start = left;
            int end = right;
            while (start < end) {
                while (start < end && source[start].compareTo(pivot) <= 0) {
                    start++;
                }
                while (start < end && source[end].compareTo(pivot) >= 0) {
                    end--;
                }
                swap(source, start, end);
            }
            swap(source,start,(left + right) / 2);
            sort(source,left,start-1);
            sort(source,start+1,right);
        }
    }

    public <T extends Comparable<T>> void swap(T[] source, int left, int right) {
        T tmp = source[left];
        source[left] = source[right];
        source[right] = tmp;
    }
}
