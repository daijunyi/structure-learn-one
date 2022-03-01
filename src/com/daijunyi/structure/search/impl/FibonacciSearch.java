package com.daijunyi.structure.search.impl;

import com.daijunyi.structure.search.Search;

import java.util.Arrays;
import java.util.List;

/**
 * @author djy
 * @createTime 2022/2/28 下午5:00
 * @description 斐波那契查找
 */
public class FibonacciSearch implements Search {
    @Override
    public int indexOf(int[] list, int query) {
        int left = 0;
        int right = list.length-1;
        int[] f = fib();
        int k = 0;
        //得到是第几个斐波那契
        while (right > f[k]-1){
            k++;
        }
        //进行原来数组扩充
        int[] tmp = Arrays.copyOf(list,f[k]);
        //tmp现在里面的最后那些是用0填充的，我们需要改为最高位填充
        for (int i=right;i<tmp.length;i++){
            tmp[i] = tmp[right];
        }

        //开始查找
        while (left <= right){
            int mid = left+f[k-1]-1;
            int result = tmp[mid];
            if (query < result){
                //向左
                right = mid-1;
                k--;
                //解释 因为f[k] = f[k-1]+f[k-2] => f[k-2]+f[k-1]=f[k];  这里我们就可以这样理解 f[k-2] 相当于left f[k-1]相当于right
                //因为我们是左移动所以我们要把修改right位置 相当于我们要移动到f[k-1] 所以我们要对k--
            }else if (query > result){
                //向右查找
                left = mid+1;
                k -= 2;
                //根据上面的解释，我们现在要右移动，所以我们要修改left值，所以我们就移动到f[k-2]所以k-2
            }else{
                return  mid < list.length ? mid : list.length-1;
            }
        }
        return -1;
    }

    /**
     * 获取一个斐波那契数列
     * @return
     */
    public int[] fib(){
        int[] fib = new int[20];
        fib[0] = 1;
        fib[1] = 1;
        for (int i= 2;i<fib.length;i++){
            fib[i] = fib[i-1]+fib[i-2];
        }
        return fib;
    }

    @Override
    public List<Integer> indexOfAll(int[] list, int query) {
        int left = 0;
        int right = list.length-1;
        int[] f = fib();
        int k = 0;
        //得到是第几个斐波那契
        while (right > f[k]-1){
            k++;
        }
        //进行原来数组扩充
        int[] tmp = Arrays.copyOf(list,f[k]);
        //tmp现在里面的最后那些是用0填充的，我们需要改为最高位填充
        for (int i=right;i<tmp.length;i++){
            tmp[i] = tmp[right];
        }

        //开始查找
        while (left <= right){
            int mid = left+f[k-1]-1;
            int result = tmp[mid];
            if (query < result){
                //向左
                right = mid-1;
                k--;
                //解释 因为f[k] = f[k-1]+f[k-2] => f[k-2]+f[k-1]=f[k];  这里我们就可以这样理解 f[k-2] 相当于left f[k-1]相当于right
                //因为我们是左移动所以我们要把修改right位置 相当于我们要移动到f[k-1] 所以我们要对k--
            }else if (query > result){
                //向右查找
                left = mid+1;
                k -= 2;
                //根据上面的解释，我们现在要右移动，所以我们要修改left值，所以我们就移动到f[k-2]所以k-2
            }else{
                mid = mid < list.length ? mid : list.length-1;
                return getAllByMiddle(list,query,mid);
            }
        }
        return null;
    }
}
