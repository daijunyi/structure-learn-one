package com.daijunyi.structure.sort;

class DonaldShellSortMain {
    public static void main(String[] args) {
        SortUtil.smallTest(new DonaldShellSort());
        SortUtil.middleTest(new DonaldShellSort());
        SortUtil.bigTest(new DonaldShellSort());
    }
}

/**
 * @author djy
 * @createTime 2022/1/7 下午3:35
 * @description 希尔排序  插入排序的一种高效版本
 */
public class DonaldShellSort implements Sort {

    /**
     * 希尔排序是把记录按下标的一定增量分组，
     * 对每组使用直接插入排序算法排序，随着增量渐渐减少，
     * 每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，最后排序一次，算法便终止。
     *
     * @param source
     * @param <T>
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        T tmp;
        //增量值
        for (int gap = source.length / 2; gap > 0; gap /= 2) {
            //分组的最后一排数据
            for (int i = gap; i < source.length; i++) {
//                //这里使用了交换 效率反而慢
//                for (int j = i - gap; j >= 0; j -= gap) {
//                    if (source[j].compareTo(source[j + gap]) > 0) {
//                        tmp = source[j];
//                        source[j] = source[j + gap];
//                        source[j + gap] = tmp;
//                    }
//                }
                //优化使用 改成插入
                int j = i;
                tmp = source[j];
                while (j-gap >= 0 && tmp.compareTo(source[j-gap]) < 0){
                    source[j] = source[j-gap];
                    j = j-gap;
                }
                source[j] = tmp;
            }
        }
    }
}
