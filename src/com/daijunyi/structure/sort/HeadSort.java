package com.daijunyi.structure.sort;

/**
 * @author djy
 * @createTime 2022/5/16 上午11:59
 * @description 堆排序
 */

class HeadSortMain {
    public static void main(String[] args) {
        HeadSort headSort = new HeadSort();
        Integer[] waitSortArray = {4, 6, 8, 5, 9,-1,800,-20,30};
        headSort.sort(waitSortArray);
        System.out.println("排序完结果：");
        for (int i=0;i<waitSortArray.length;i++){
            System.out.print(waitSortArray[i]+",");
        }
        SortUtil.smallTest(headSort);
        //1万数据测试
        SortUtil.sort(headSort,10000);
        //10万数据测试
        SortUtil.sort(headSort,100000);
        //100万数据测试
        SortUtil.sort(headSort,1000000);
        //800万数据
        SortUtil.bigTest(headSort);
    }
}

public class HeadSort implements Sort {

    /**
     * 堆排序升序，使用大顶堆， 大顶堆就是 非叶子节点，大于他的两个子节点，最顶部的是最大的值 实现思路 把数组整理成一个大顶堆，从最左边的的最底部的非叶子节点开始整理，
     * 成为大顶堆之后，把最顶部的数和数组的最后一个数进行置换，然后再重新进行整理
     * @param source
     * @param <T>
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        //1、调整为大顶堆，从最左边的最底部的非叶子节点开始
        for (int i = source.length / 2 - 1; i >= 0; i--) {
            adjustHead(source, i, source.length);
        }
        //2，进行交换，再调整为大顶堆
        for (int length = source.length - 1; length > 0; length--) {
            T tmp = source[length];
            source[length] = source[0];
            source[0] = tmp;
            adjustHead(source,0,length);
        }
    }

    /**
     * 以某个非叶子节点进行整理
     * @param source
     * @param i
     * @param length
     * @param <T>
     */
    public <T extends Comparable<T>> void adjustHead(T[] source, int i, int length) {
        T temp = source[i];
        //左子节点
        //从当前给的非叶子节点的左节点遍历到当前节点的下一个叶子节点
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            //k+1 为i的下边的右节点。如果当前是左节点小于右节点，那就让右边的节点和非叶子节点进行进行比较
            if (k + 1 < length && source[k].compareTo(source[k + 1]) < 0) {
                k++;
            }
            //如果非叶子节点小于下边比较大的子节点，就相互交换，并且赋值新位置
            if (temp.compareTo(source[k]) < 0) {
                source[i] = source[k];
                i = k;
            } else {
                //因为我们的原则是从最底部的左边的非叶子节点开始的，所以此处就可以直接break
                break;
            }
        }
        source[i] = temp;
    }
}
