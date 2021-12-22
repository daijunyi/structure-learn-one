package com.daijunyi.structure.linked.pojo;

/**
 * @author djy
 * @createTime 2021/12/22 下午1:57
 * @description
 */
public class HeroDoubleNode {

    public int id;
    public String name;
    public String nickName;
    /**
     * 下一个节点
     */
    public HeroDoubleNode next;
    /**
     * 上一个节点
     */
    public HeroDoubleNode pre;

    public HeroDoubleNode() {

    }

    public HeroDoubleNode(Integer id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
