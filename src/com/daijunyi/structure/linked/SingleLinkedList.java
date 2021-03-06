package com.daijunyi.structure.linked;

import com.daijunyi.structure.linked.pojo.HeroNode;


/**
 * @author djy
 * @createTime 2021/12/21 下午2:25
 * @description
 */
class SingleLinkedListMain {
    /**
     * 单链表
     * @param args
     */
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(new HeroNode(1, "宋江", "及时雨"));
        linkedList.add(new HeroNode(2,"卢俊义","玉麒麟"));
        linkedList.add(new HeroNode(3,"吴用","智多星"));
        linkedList.add(new HeroNode(4,"林冲","豹子头"));

        linkedList.addByOrder(new HeroNode(50, "燕顺", "锦毛虎"));
        linkedList.addByOrder(new HeroNode(40,"宣赞","丑郡马"));
        linkedList.addByOrder(new HeroNode(7,"秦明","霹雳火"));
        linkedList.addByOrder(new HeroNode(30,"张顺","浪里白条"));
        linkedList.addByOrder(new HeroNode(4,"林冲","豹子头"));
        linkedList.printList();

        linkedList.update(new HeroNode(4,"公孙胜","入云龙"));
        System.out.println("更新后");
        linkedList.printList();


        linkedList.delete(30);
        System.out.println("删除后");
        linkedList.printList();;

        System.out.printf("%d\t条数据",linkedList.length());

        System.out.println("反转");
        linkedList.reversal();
        linkedList.printList();

        System.out.println("简单一次翻转");
        linkedList.simpleReversal();
        linkedList.printList();
    }
}

/**
 * @author djy
 * @createTime 2021/12/21 下午2:12
 * @description
 */
public class SingleLinkedList {

    private final HeroNode headNode = new HeroNode();

    /**
     * 添加在最后
     * @param node
     * @return
     */
    public boolean add(HeroNode node) {
        if (node == null) {
            return false;
        }
        HeroNode tmp = headNode;
        while (tmp.next != null) {
            if (node.id == tmp.next.id) {
                System.out.println("已经存在相同的节点不能重复添加" + node);
                return false;
            }
            tmp = tmp.next;
        }
        tmp.next = node;
        return true;
    }

    /**
     * 排序添加
     * @param node
     * @return
     */
    public boolean addByOrder(HeroNode node) {
        if (node == null) {
            return false;
        }
        //1、与下一个节点的id进行比对是否是小于该节点，如果是小于的就就添加在该节点前面
        HeroNode tmp = headNode;
        while (tmp.next != null) {
            if (node.id < tmp.next.id) {
                node.next = tmp.next;
                tmp.next = node;
                return true;
            } else if (node.id == tmp.next.id) {
                System.out.println("已经存在相同的节点,无法重复添加" + node);
                return false;
            }
            tmp = tmp.next;
        }
        tmp.next = node;
        return true;
    }

    /**
     * 更新
     * @param node
     * @return
     */
    public boolean update(HeroNode node) {
        if (node == null) {
            return false;
        }
        HeroNode tmp = headNode.next;
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
     * 删除
     * @param id
     * @return
     */
    public boolean delete(int id){
        HeroNode tmp = headNode;
        while (tmp.next != null){
            if (tmp.next.id == id){
                //删除
                tmp.next = tmp.next.next;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    /**
     * 一次翻转
     */
    public void simpleReversal(){
        HeroNode head = headNode;
        if (head.next == null || head.next.next == null){
            return;
        }

        HeroNode tmpHead = new HeroNode();
        HeroNode currentNode = head.next;
        HeroNode next = null;
        while (currentNode != null){
            next = currentNode.next;
            currentNode.next = tmpHead.next;
            tmpHead.next = currentNode;
            currentNode = next;
        }
        head.next = tmpHead.next;
    }

    /**
     * 反转链表 //简单除暴的翻转逻辑
     */
    public void reversal(){
        HeroNode head = headNode;
        if (head.next == null){
            return;
        }
        //临时新头部节点
        HeroNode tmpHead = new HeroNode();
        tmpHead.next = head.next;
        head.next = null;
        HeroNode tmp = null;
        //获取最后一个并且把最后一个添加到后一个尾部
        while ((tmp = getLastAndRemoveLast(tmpHead)) != null){
            addByHead(head,tmp);
        }
    }

    public void addByHead(HeroNode head,HeroNode node){
        if (node == null) {
            return;
        }
        HeroNode tmp = head;
        while (tmp.next != null) {
            if (node.id == tmp.next.id) {
                System.out.println("已经存在相同的节点不能重复添加" + node);
                return;
            }
            tmp = tmp.next;
        }
        tmp.next = node;
    }

    public HeroNode getLastAndRemoveLast(HeroNode head){
        if (head == null || head.next == null){
            return null;
        }
        HeroNode tmp = head;
        while (tmp.next != null){
            if (tmp.next.next == null){
                HeroNode last = tmp.next;
                tmp.next = null;
                return last;
            }
            tmp = tmp.next;
        }
        head.next = null;
        return tmp;
    }

    /**
     * 节点个数
     * @return
     */
    public int length(){
        HeroNode tmp = headNode;
        int count = 0;
        while (tmp.next != null){
            count++;
            tmp = tmp.next;
        }
        return count;
    }

    public void printList() {
        HeroNode tmp = headNode.next;
        if (tmp == null) {
            System.out.println("数据为空");
            return;
        }

        do {
            System.out.println(tmp);
            tmp = tmp.next;
        } while (tmp != null);
    }

}
