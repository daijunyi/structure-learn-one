package com.daijunyi.structure.stack;

import com.daijunyi.structure.linked.LinkedList;

class SingleLinkedStackMain{
    public static void main(String[] args) {
        SingleLinkedStack<Integer> stack = new SingleLinkedStack<>(3);
        System.out.printf("加入数据:%d",stack.push(1));
        System.out.printf("加入数据:%d",stack.push(2));
        System.out.printf("加入数据:%d",stack.push(3));
        System.out.printf("取出数据:%d",stack.pop());
        System.out.printf("取出数据:%d",stack.pop());
        System.out.printf("取出数据:%d",stack.pop());
    }
}

/**
 * @author djy
 * @createTime 2021/12/27 下午7:26
 * @description 单链表栈
 */
public class SingleLinkedStack<T> {

    private int maxSize;

    private LinkedList<T> list = new LinkedList<>();

    private int top;

    public SingleLinkedStack(int maxSize){
        this.maxSize = maxSize;
        top = -1;
    }

    public boolean isFull(){
        return top+1 == maxSize;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public T push(T element){
        if (isFull()){
            System.out.println("已经满了没法再增加了");
            return null;
        }

        list.addFront(element);
        top++;
        return element;
    }

    public T pop(){
        if (isEmpty()){
            System.out.println("已经没有数据可以退出了");
            return null;
        }
        T first = list.first();
        list.remove(first);
        return first;
    }
}


