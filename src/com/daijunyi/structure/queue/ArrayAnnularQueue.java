package com.daijunyi.structure.queue;


import java.util.Scanner;

class ArrayAnnularQueueMain{
    public static void main(String[] args) {
        ArrayAnnularQueue arrayQueue = new ArrayAnnularQueue(5);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("l(length):查看队列个数");
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

                    try {
                        String next = scanner.next();
                        int number = Integer.parseInt(next);
                        arrayQueue.addQueue(number);
                    } catch (Exception e){
                        System.out.println("请输入有效的数字，请重新选择您的操作");
                    }

                    break;
                case 'g':
                    int queue = 0;
                    try {
                        queue = arrayQueue.getQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.printf("值：%d\t",queue);
                    System.out.println();
                    break;
                case 'h':
                    arrayQueue.headQueue();
                    break;
                case 'l':
                    int length = arrayQueue.length();
                    System.out.printf("队列个数%d\t",length);
                    System.out.println();
                    break;
                default:

            }
        }
        System.out.println("程序退出");
    }
}

/**
 * 数组环形队列
 * ● front变量：指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素，front初始值0
 * ● rear变量：指向队列的最后一个元素的后一个位置，空出一个空间作为约定，好计算，rear的初始值=0
 * ● 队列满：条件(rear+1)%maxSize = front【满】
 * ● 队列空:rear==front【空】
 * ● 队列中值个数：(rear+maxSzie-front)%maxSize
 * @author djy
 * @createTime 2021/12/20 下午7:36
 * @description
 */
public class ArrayAnnularQueue {
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


    public ArrayAnnularQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        //指向队列头部，分析出front是指向队列头的第一个位置
        front = 0;
        //指向队列尾，指向队列尾的数据的后一个位置
        rear = 0;
    }

    /**
     * 队列是否满了
     * @return
     */
    public boolean isFull() {
        return (rear+1)%maxSize == front;
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
        arr[rear] = value;
        rear = (rear+1)%maxSize;
        return true;
    }

    /**
     * 获取队列
     * @return
     */
    public int getQueue() throws Exception {
        if (isEmpty()) {
            System.out.println("队列为空，没有更多数据了");
            throw new Exception("队列空了");
        }
        int result = arr[front];
        arr[front] = 0;
        front = (front+1)%maxSize;
        return result;
    }

    /**
     * 显示头部数据
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空了，没有数据了");
        }
        return arr[front];
    }

    public int length(){
        return (rear+maxSize-front)%maxSize;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的");
            return;
        }
        for (int i = 0; i < maxSize; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }
}
