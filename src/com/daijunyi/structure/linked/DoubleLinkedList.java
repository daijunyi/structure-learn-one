package com.daijunyi.structure.linked;

import com.daijunyi.structure.linked.pojo.HeroDoubleNode;

class DoubleLinkedListMain {

    /**
     * 单链表
     * @param args
     */
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(new HeroDoubleNode(1, "宋江", "及时雨"));
        linkedList.add(new HeroDoubleNode(2,"卢俊义","玉麒麟"));
        linkedList.add(new HeroDoubleNode(3,"吴用","智多星"));
        linkedList.add(new HeroDoubleNode(4,"林冲","豹子头"));

        linkedList.addByOrder(new HeroDoubleNode(50, "燕顺", "锦毛虎"));
        linkedList.addByOrder(new HeroDoubleNode(40,"宣赞","丑郡马"));
        linkedList.addByOrder(new HeroDoubleNode(7,"秦明","霹雳火"));
        linkedList.addByOrder(new HeroDoubleNode(30,"张顺","浪里白条"));
        linkedList.addByOrder(new HeroDoubleNode(4,"林冲","豹子头"));
        linkedList.printList();

        linkedList.update(new HeroDoubleNode(4,"公孙胜","入云龙"));
        System.out.println("更新后");
        linkedList.printList();


        linkedList.delete(30);
        System.out.println("删除后");
        linkedList.printList();;

        System.out.printf("%d\t条数据", linkedList.length());

        System.out.println("简单一次翻转");
        linkedList.simpleReversal();
        linkedList.printList();
    }
}

/**
 * @author djy
 * @createTime 2021/12/22 下午1:47
 * @description
 */
public class DoubleLinkedList {

    private final HeroDoubleNode headNode = new HeroDoubleNode();

    /**
     * 添加在最后
     * @param node
     * @return
     */
    public boolean add(HeroDoubleNode node) {
        if (node == null) {
            return false;
        }
        HeroDoubleNode tmp = headNode;
        while (tmp.next != null) {
            if (node.id == tmp.next.id) {
                System.out.println("已经存在相同的节点不能重复添加" + node);
                return false;
            }
            tmp = tmp.next;
        }
        tmp.next = node;
        node.pre = tmp;
        return true;
    }

    /**
     * 排序添加
     * @param node
     * @return
     */
    public boolean addByOrder(HeroDoubleNode node) {
        if (node == null) {
            return false;
        }
        //1、与下一个节点的id进行比对是否是小于该节点，如果是小于的就就添加在该节点前面
        HeroDoubleNode tmp = headNode;
        while (tmp.next != null) {
            if (node.id < tmp.next.id) {
                node.next = tmp.next;
                tmp.next.pre = node;
                node.pre = tmp;
                tmp.next = node;
                return true;
            } else if (node.id == tmp.next.id) {
                System.out.println("已经存在相同的节点,无法重复添加" + node);
                return false;
            }
            tmp = tmp.next;
        }
        tmp.next = node;
        node.pre = tmp;
        return true;
    }

    /**
     * 更新
     * @param node
     * @return
     */
    public boolean update(HeroDoubleNode node) {
        if (node == null) {
            return false;
        }
        HeroDoubleNode tmp = headNode.next;
        while (tmp != null) {
            if (tmp.id == node.id) {
                tmp.name = node.name;
                tmp.nickName = node.nickName;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    /**
     * 删除双向链表可双向删除
     * @param id
     * @return
     */
    public boolean delete(int id){
        HeroDoubleNode tmp = headNode.next;
        while (tmp != null){
            if (tmp.id == id){
                //删除
                tmp.pre.next = tmp.next;
                if (tmp.next != null){
                    //最后一个节点的时候
                    tmp.next.pre = tmp.pre;
                }
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    /**
     * 节点个数
     * @return
     */
    public int length(){
        HeroDoubleNode tmp = headNode;
        int count = 0;
        while (tmp.next != null){
            count++;
            tmp = tmp.next;
        }
        return count;
    }

    public void printList() {
        HeroDoubleNode tmp = headNode.next;
        if (tmp == null) {
            System.out.println("数据为空");
            return;
        }

        do {
            System.out.println(tmp);
            tmp = tmp.next;
        } while (tmp != null);
    }

    /**
     * 一次翻转
     */
    public void simpleReversal(){
        HeroDoubleNode head = headNode;
        if (head.next == null || head.next.next == null){
            return;
        }

        HeroDoubleNode tmpHead = new HeroDoubleNode();
        HeroDoubleNode currentNode = head.next;
        HeroDoubleNode next = null;
        while (currentNode != null){
            next = currentNode.next;
            //从原来的链表自我删除
            if (currentNode.next != null){
                currentNode.next.pre = currentNode.pre;
            }
            currentNode.pre.next = currentNode.next;
            //插入到新添加第一个上
            //先挂新节点后面的链表到当前节点上
            currentNode.next = tmpHead.next;
            if (tmpHead.next != null){
                tmpHead.next.pre = currentNode;
            }
            //挂载自己到临时新头节点上
            tmpHead.next = currentNode;
            currentNode.pre = tmpHead;

            currentNode = next;
        }
        head.next = tmpHead.next;
        tmpHead.next.pre = head;
    }

}


