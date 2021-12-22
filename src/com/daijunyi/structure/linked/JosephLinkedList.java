package com.daijunyi.structure.linked;

import com.daijunyi.structure.linked.pojo.JosephBoy;
import com.sun.scenario.animation.shared.ClipEnvelope;

class JosephLinkedListMain {
    public static void main(String[] args) {
        JosephLinkedList linkedList = new JosephLinkedList(5);
        linkedList.print();
        linkedList.countBoy(1,2);
    }
}

/**
 * @author djy
 * @createTime 2021/12/22 下午2:58
 * @description
 */
public class JosephLinkedList {

    private JosephBoy first = null;

    private int size;

    /**
     * 创建一个环形单向链表
     * @param size
     */
    public JosephLinkedList(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("最少大于1个");
        }
        this.size = size;
        //临时
        JosephBoy tmp = null;
        for (int i = 0; i < size; i++) {
            JosephBoy boy = new JosephBoy(i+1);
            if (i == 0) {
                first = boy;
            } else {
                tmp.setNext(boy);
            }
            tmp = boy;
        }
        tmp.setNext(first);
    }

    public void print(){
        JosephBoy tmp = first;
        while (true){
            System.out.printf("%d\t",tmp.getNo());
            if (tmp.getNext() == first){
                break;
            }
            tmp = tmp.getNext();
        }
    }

    public void countBoy(int startNo,int countNum){
        if (first == null || startNo < 1 || startNo >= size){
            throw new IllegalArgumentException("参数异常");
        }

        //因为是单向链表所以，在出圈的时候需要知道自己的上一个节点，单向链表不能自我删除，所以需要先找出最后一个节点
        JosephBoy tmp = first;
        while (true){
            if (tmp.getNext() == first){
                break;
            }
            tmp = tmp.getNext();
        }

        //找到从第几个小孩开始喊
        for (int i = 0;i<startNo-1;i++){
            first = first.getNext();
            tmp = tmp.getNext();
        }


        while (true){
            if (first.getNext() == first){
                break;
            }
            //喊两次
            for (int i=0;i<countNum-1;i++){
                first = first.getNext();
                tmp = tmp.getNext();
            }

            System.out.printf("%d->",first.getNo());
            first = first.getNext();
            tmp.setNext(first);
        }
        System.out.printf("%d",first.getNo());
    }

}
