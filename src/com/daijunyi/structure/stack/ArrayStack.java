package com.daijunyi.structure.stack;

class ArrayStackMain{
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(3);
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
 * @createTime 2021/12/25 上午7:47
 * @description 用数组实现栈
 */
public class ArrayStack<T> {

    private Object[] array;

    private int maxSize;

    private int top;

    public ArrayStack(int maxSize){
        array = new Object[maxSize];
        this.maxSize = maxSize;
        top = -1;
    }

    public Boolean isFull(){
        return maxSize-1 == top;
    }

    public Boolean isEmpty(){
        return top == -1;
    }

    public T push(T element){
        if (isFull()){
            System.out.println("栈满了没有数据了");
            return null;
        }
        array[++top] = element;
        return element;
    }

    public T pop(){
        if (isEmpty()){
            System.out.println("栈中没有数据了");
            return null;
        }
        Object o = array[top];
        top--;
        return (T) o;
    }

    public T peek(){
        if (isEmpty()){
            System.out.println("栈中没有数据了");
            return null;
        }
        return (T) array[top];
    }
}
