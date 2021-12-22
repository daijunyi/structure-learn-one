package com.daijunyi.structure.linked.pojo;

/**
 * @author djy
 * @createTime 2021/12/21 下午2:13
 * @description
 */
public class HeroNode {
    public int id;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode() {

    }

    public HeroNode(Integer id, String name, String nickName) {
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
