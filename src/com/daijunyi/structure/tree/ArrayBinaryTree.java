package com.daijunyi.structure.tree;

class ArrayBinaryTreeMain{
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}
/**
 * @author djy
 * @createTime 2022/3/3 下午5:06
 * @description
 */
public class ArrayBinaryTree {

    private int[] arr = null;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    public void preOrder(int index){
        if (arr == null || index >= arr.length){
            return;
        }
        System.out.println(arr[index]);
        preOrder(2*index+1);
        preOrder(2*index+2);
    }
}
