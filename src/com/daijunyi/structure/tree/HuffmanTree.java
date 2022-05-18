package com.daijunyi.structure.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author djy
 * @createTime 2022/5/18 下午4:03
 * @description 赫夫曼树
 */

class HuffmanTreeMain {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        HuffmanNode huffmanNode = HuffmanTree.getHuffmanNode(array);
        HuffmanTree.preOrder(huffmanNode);
    }
}

/**
 * 赫夫曼树构建类
 */
public class HuffmanTree {

    /**
     * 获取赫夫曼二叉树
     * @param array
     * @return
     */
    public static HuffmanNode getHuffmanNode(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("参数非法");
        }
        //第一步把数组转换成node节点
        ArrayList<HuffmanNode> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(new HuffmanNode(array[i]));
        }
        Collections.sort(list);
        //只要集合结果大于1就可以再次循环
        System.out.println("排序之后结果:" + list);
        int index = 0;
        while (list.size() > 1) {
            index++;
            //开始组成哈夫曼树
            HuffmanNode leftNode = list.get(0);
            HuffmanNode rightNode = list.get(1);
            HuffmanNode parentNode = new HuffmanNode(leftNode.getValue() + rightNode.getValue());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            //删除原来集合总的前两个数据，并且添加新的根节点
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parentNode);
            Collections.sort(list);
            System.out.println("排序轮次" + index + "结果：" + list);
        }
        return list.get(0);
    }

    /**
     * 前序遍历
     * @param node
     */
    public static void preOrder(HuffmanNode node){
        System.out.println("值:"+node.getValue());
        if (node.getLeft() != null){
            preOrder(node.getLeft());
        }
        if (node.getRight() != null){
            preOrder(node.getRight());
        }
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {

    public HuffmanNode(int value) {
        this.value = value;
    }

    private int value;
    private HuffmanNode left;
    private HuffmanNode right;

    public int getValue() {
        return value;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }
}
