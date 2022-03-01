package com.daijunyi.structure.sort;

import com.daijunyi.structure.sort.quick.QuickSwapSort;

import java.util.LinkedHashMap;

/**
 * @author djy
 * @createTime 2022/1/9 下午3:46
 * @descriptionHa
 */
public class SortMain {
    public static void main(String[] args) {
//        SortUtil.sort(new QuickSwapSort(),1000);
//        SortUtil.sort(new QuickSwapSort(),5000);
//        SortUtil.sort(new QuickSwapSort(),10000);
//        SortUtil.sort(new QuickSwapSort(),100000);
//        SortUtil.sort(new QuickSwapSort(),1000000);
//        SortUtil.sort(new QuickSwapSort(),10000000);
//        SortUtil.sort(new QuickSwapSort(),50000000);
//        SortUtil.sort(new QuickSwapSort(),100000000);
//
//        SortUtil.sort(new QuickSwapSort(),1000);
//        SortUtil.sort(new QuickSwapSort(),5000);
//        SortUtil.sort(new QuickSwapSort(),10000);
//        SortUtil.sort(new QuickSwapSort(),100000);
//        SortUtil.sort(new QuickSwapSort(),1000000);
//        SortUtil.sort(new QuickSwapSort(),10000000);
//        SortUtil.sort(new QuickSwapSort(),50000000);
//        SortUtil.sort(new QuickSwapSort(),100000000);
//
//
//
//        SortUtil.sort(new MergeSort(),1000);
//        SortUtil.sort(new MergeSort(),5000);
//        SortUtil.sort(new MergeSort(),10000);
//        SortUtil.sort(new MergeSort(),100000);
//        SortUtil.sort(new MergeSort(),1000000);
//        SortUtil.sort(new MergeSort(),10000000);
//        SortUtil.sort(new MergeSort(),50000000);
//        SortUtil.sort(new MergeSort(),100000000);
//
//        SortUtil.sortInt(1000);
//        SortUtil.sortInt(5000);
//        SortUtil.sortInt(10000);
//        SortUtil.sortInt(100000);
//        SortUtil.sortInt(1000000);
//        SortUtil.sortInt(10000000);
//        SortUtil.sortInt(50000000);
//        SortUtil.sortInt(100000000);

        int HASH_BITS = 0x7fffffff;
        System.out.println(HASH_BITS);

        String string = new String("1");
        System.out.println("hash值："+string.hashCode());

        LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
        objectObjectLinkedHashMap.put("1","1");
    }
}

class Foo {
    final int i = 0;
    int j;
    public void doSomething() {
        System.out.println(++j + i);
    }

    public static void main(String[] args) {
        new Foo().doSomething();

        String s = "祝你考出好成绩！";
        System.out.println(s.length());
    }
}
