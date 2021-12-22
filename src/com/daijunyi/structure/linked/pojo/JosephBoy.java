package com.daijunyi.structure.linked.pojo;

/**
 * @author djy
 * @createTime 2021/12/22 下午3:00
 * @description
 */
public class JosephBoy {

    private int no;
    private JosephBoy next;

    public JosephBoy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public JosephBoy getNext() {
        return next;
    }

    public void setNext(JosephBoy next) {
        this.next = next;
    }
}
