package com.daijunyi.structure.tree;

class BinaryTreeMain{
    public static void main(String[] args) {
        Node root = new Node(1, "张一");
        Node node1 = new Node(2, "张三");
        Node node2 = new Node(3, "李四");
        Node node3 = new Node(4,"王5");
        Node node4 = new Node(5,"某3");
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);
        node2.setLeft(node4);

        System.out.println("前序");
        binaryTree.preOrder();
        System.out.println();
        System.out.println("中序");
        binaryTree.infixOrder();
        System.out.println();
        System.out.println("后序");
        binaryTree.postOrder();

        System.out.println();
        System.out.println("开始前序查找");
        binaryTree.preOrderSearch(5);
        System.out.println();
        System.out.println("开始中序查找");
        binaryTree.infixOrderSearch(5);
        System.out.println();
        System.out.println("开始后序查找");
        binaryTree.postOrderSearch(5);

    }
}


/**
 * @author djy
 * @createTime 2022/3/3 上午10:36
 * @description 二叉树
 */
public class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if (root != null){
            root.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (root != null){
            root.postOrder();
        }
    }

    public Node preOrderSearch(Integer no){
        if (root != null){
            Node node = root.preOrderSearch(no);
            if (node == null){
                System.out.println("没有查找到 no:"+no);
            }else{
                System.out.println("查找到no"+node);
            }
            return node;
        }
        return null;
    }

    public Node infixOrderSearch(Integer no){
        if (root != null){
            Node node = root.infixOrderSearch(no);
            if (node == null){
                System.out.println("没有查找到 no:"+no);
            }else{
                System.out.println("查找到no"+node);
            }
            return node;
        }
        return null;
    }


    public Node postOrderSearch(Integer no){
        if (root != null){
            Node node = root.postOrderSearch(no);
            if (node == null){
                System.out.println("没有查找到 no:"+no);
            }else{
                System.out.println("查找到no"+node);
            }
            return node;
        }
        return null;
    }

}


class Node{
    private Integer no;

    private String name;

    private Node left;

    private Node right;

    public Node(Integer no, String name) {
        this.no = no;
        this.name = name;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (left != null){
            left.preOrder();;
        }
        if (right != null){
            right.preOrder();;
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (left != null){
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null){
            right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (left != null){
            left.postOrder();
        }
        if (right != null){
            right.postOrder();
        }
        System.out.println(this);
    }

    public Node preOrderSearch(Integer no){
        System.out.println("前序查找");
        if (this.no.intValue() == no.intValue()){
            return this;
        }
        Node findNode = null;
        if (left != null){
            findNode = left.preOrderSearch(no);
        }
        if (findNode != null){
            return findNode;
        }
        if (right != null){
            findNode = right.preOrderSearch(no);
        }
        return findNode;
    }

    public Node infixOrderSearch(Integer no){
        Node findNode = null;
        if (left != null){
            findNode = left.infixOrderSearch(no);
        }
        if (findNode != null){
            return findNode;
        }
        System.out.println("中序查找");
        if (this.no.intValue() == no.intValue()){
            return this;
        }
        if (right != null){
            findNode = right.infixOrderSearch(no);
        }
        return findNode;
    }


    public Node postOrderSearch(Integer no){
        Node findNode = null;
        if (left != null){
            findNode = left.postOrderSearch(no);
        }
        if (findNode != null){
            return findNode;
        }
        if (right != null){
            findNode = right.postOrderSearch(no);
        }
        if (findNode != null){
            return findNode;
        }
        System.out.println("后序查找");
        if (this.no.intValue() == no.intValue()){
            return this;
        }
        return findNode;
    }

}