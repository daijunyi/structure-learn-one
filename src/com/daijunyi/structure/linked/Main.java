package com.daijunyi.structure.linked;


/**
 * @author djy
 * @createTime 2021/12/21 下午2:25
 * @description
 */
public class Main {
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
