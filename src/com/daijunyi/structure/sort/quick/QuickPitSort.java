package com.daijunyi.structure.sort.quick;

import com.daijunyi.structure.sort.Sort;
import com.daijunyi.structure.sort.SortUtil;

class QuickSortMain {
    public static void main(String[] args) {
        SortUtil.smallTest(new QuickPitSort());
        SortUtil.middleTest(new QuickPitSort());
        SortUtil.bigTest(new QuickPitSort());
    }
}

/**
 * @author djy
 * @createTime 2022/1/8 上午9:35
 * @description 快速排序之填坑
 */
public class QuickPitSort implements Sort {

    /**
     * 填坑法
     * @param source
     * @param <T>
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        sort(source, 0, source.length - 1);
    }

    private <T extends Comparable> void sort(T[] source, int left, int right) {
        if (left < right) {
            int start = left,end = right;
            T pivot = source[left];
            while (start < end){
                //从后部开始找到比标兵值小的
                while (start < end && source[end].compareTo(pivot) >= 0){
                    end--;
                }
                //把找到的小于标兵的值，复制给坑位，然后小的值的位置变成新的坑位
                source[start] = source[end];
                //从前面找到比标兵大的，复制给坑位
                while (start < end && source[start].compareTo(pivot) <= 0){
                    start++;
                }
                source[end] = source[start];
            }
            //把标兵复制给最后一个坑位
            source[start] = pivot;
            //对标兵左边递归快速排序
            sort(source,left,start-1);
            //对标兵右边递归快速排序
            sort(source,start+1,right);
        }
    }

}
