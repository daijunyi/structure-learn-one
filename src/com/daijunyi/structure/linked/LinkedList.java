package com.daijunyi.structure.linked;

/**
 * @author djy
 * @createTime 2021/12/27 下午7:33
 * @description
 */
public class LinkedList<T> {

    private Node<T> head = new Node(null);

    private int size = 0;

    public boolean add(T element){
        if (element == null){
            return false;
        }
        Node tmp = head;
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = new Node<T>(element);
        size++;
        return true;
    }

    /**
     * 添加到第一个
     * @param element
     * @return
     */
    public boolean addFront(T element){
        if (element == null){
            return false;
        }
        Node<T> tNode = new Node<>(element);
        tNode.next = head.next;
        head.next = tNode;
        return true;
    }

    /**
     * 根据角标添加
     * @param element
     * @param index
     * @return
     */
    public boolean add(T element,int index){
        if (element == null){
            return false;
        }

        int currentIndex = 0;
        Node tmp = head;
        while (tmp.next != null){
            if (currentIndex == index){
                Node<T> tNode = new Node<>(element);
                tNode.next = tmp.next;
                tmp.next = tNode;
                size++;
                return true;
            }
            currentIndex++;
            tmp = tmp.next;
        }
        throw new IndexOutOfBoundsException("角标越界");
    }

    public T first(){
        return head.next.obj;
    }

    public boolean remove(T element){
        if (element == null){
            return false;
        }
        Node<T> tmp = head;
        while (tmp.next != null){
            if (tmp.next.obj == element){
                tmp.next = tmp.next.next;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    private class Node<T>{
        T obj;

        Node<T> next;

        public Node(T obj) {
            this.obj = obj;
        }
    }

}
