package com.daijunyi.structure.queue;


import java.util.Scanner;

/**
 * @author djy
 * @createTime 2021/12/20 下午5:16
 * @description
 */

class ArrayQueueMain {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    int queue = arrayQueue.getQueue();
                    System.out.printf("值：%d\t",queue);
                    System.out.println();
                    break;
                case 'h':
                    arrayQueue.headQueue();
                    break;
                default:

            }
        }
        System.out.println("程序退出");
    }
}

public class ArrayQueue {


    /**
     * 表示数组最大的容量
     */
    private int maxSize;

    /**
     * 队列头
     */
    private int front;

    /**
     * 队列尾
     */
    private int rear;

    /**
     * 存储数据
     */
    private int[] arr;


    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        //指向队列头部，分析出front是指向队列头的前一个位置
        front = -1;
        //指向队列尾，指向队列尾的数据（即就是队列最后一个数据）
        rear = -1;
    }

    /**
     * 队列是否满了
     * @return
     */
    public boolean isFull() {
        return rear >= maxSize - 1;
    }

    /**
     * 队列是否空
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加值
     * @param value
     * @return
     */
    public boolean addQueue(int value) {
        if (isFull()) {
            System.out.println("队列满了");
            return false;
        }
        arr[++rear] = value;
        return true;
    }

    /**
     * 获取队列
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有更多数据了");
            throw new RuntimeException("队列空了");
        }
        return arr[++front];
    }

    /**
     * 显示头部数据
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空了，没有数据了");
        }
        return arr[front + 1];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的");
            return;
        }
        for (int i = front; i < rear; i++) {
            System.out.printf("%d\t", arr[i + 1]);
        }
        System.out.println();
    }
}
