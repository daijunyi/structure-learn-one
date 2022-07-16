package com.daijunyi.structure.tree.binary;

class BinarySortTreeMain {
    public static void main(String[] args) {
        BinarySortTree<Integer> tree = new BinarySortTree<>();
        int[] array = {7, 3, 10, 12, 5, 1, 9};
        for (int i : array) {
            tree.add(new BinarySortTree.Node(i));
        }

        tree.infixOrder();
        System.out.println("删除");
        tree.del(7);
        tree.infixOrder();
    }
}

/**
 * @author djy
 * @createTime 2022/5/22 上午10:12
 * @description
 */
public class BinarySortTree<T extends Comparable<T>> {

    private Node<T> root;

    /**
     * 添加节点
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 打印
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }
    }

    /**
     * 查找父节点
     * @param value
     * @return
     */
    public Node searchParentNode(T value){
        if (this.root == null){
            return null;
        }else{
            return this.root.searchParentNode(value);
        }
    }

    /**
     * 查找目标节点
     * @param value
     * @return
     */
    public Node searchTargetNode(T value){
        if (this.root == null){
            return null;
        }else{
            return this.root.searchTargetNode(value);
        }
    }

    /**
     * 删除节点
     * @param obj
     * @return
     */
    public Node del(T obj) {
        //找到父节点再找到子节点
        if (root == null) {
            return null;
        } else {
            Node targetNode = this.searchTargetNode(obj);
            if (targetNode == null){
                return null;
            }
            Node parentNode = this.searchParentNode(obj);
            if (targetNode.left == null && targetNode.right == null){
                if (parentNode == null){
                    //只有一个root节点，目标节点就是root节点的时候
                    this.root = null;
                    return targetNode;
                }
                //目标节点是叶子节点的时候
                if (parentNode.left != null && targetNode.value.compareTo(parentNode.left.value) == 0){
                    parentNode.left = null;
                    return targetNode;
                }else if (parentNode.right != null && targetNode.value.compareTo(parentNode.right.value) == 0){
                    parentNode.right = null;
                    return targetNode;
                }
            } else if (targetNode.left != null && targetNode.right != null){
                //左右两边都有节点的时候
                Node node = delRightMinNode(targetNode);
                node.left = targetNode.left;
                node.right = targetNode.right;
                if (parentNode == null){
                    this.root = node;
                }else{
                    if (parentNode.left != null && parentNode.left.value.compareTo(targetNode.value) == 0){
                        parentNode.left = node;
                    }else{
                        parentNode.right = node;
                    }
                }
                return targetNode;
            }else{
                if (parentNode == null){
                    //目标节点就是root节点
                    //只有一边有节点的时候
                    if (targetNode.left != null){
                        this.root = targetNode.left;
                        return targetNode;
                    }else{
                        this.root = targetNode.right;
                        return targetNode;
                    }
                }else{
                    if (targetNode.left != null){
                        if (parentNode.left != null && parentNode.left.value.compareTo(targetNode.value) == 0){
                            parentNode.left = targetNode.left;
                            return targetNode;
                        }else{
                            parentNode.right = targetNode.left;
                            return targetNode;
                        }
                    }else{
                        if (parentNode.left != null && parentNode.left.value.compareTo(targetNode.value) == 0){
                            parentNode.left = targetNode.right;
                            return targetNode;
                        }else{
                            parentNode.right = targetNode.right;
                            return targetNode;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 删除节点的右子节点的最小节点
     * @param node
     * @return
     */
    public Node delRightMinNode(Node node){
        Node del = node.right;
        while (del.left != null){
            del = del.left;
        }
        return del((T) del.value);
    }

    public static class Node<T extends Comparable<T>> {
        T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        /**
         * 小的在左边 大的再右边 添加节点 相同节点添加到右边
         * @param node
         */
        public void add(Node node) {
            if (node == null) {
                return;
            }

            if (node.value.compareTo(value) < 0) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }

        /**
         * 中序遍历
         */
        public void infixOrder() {
            if (left != null) {
                left.infixOrder();
            }
            System.out.println(this.value);
            if (right != null) {
                right.infixOrder();
            }
        }

        /**
         * 查找父节点
         * @param value
         * @return
         */
        public Node searchParentNode(T value) {
            if (this.value == null){
                return null;
            }
            if ((this.left != null && this.left.value.compareTo(value) == 0)
                    || (this.right != null && this.right.value.compareTo(value) == 0)) {
                return this;
            }else{
                if (value.compareTo(this.value) < 0){
                    if (this.left == null){
                        return null;
                    }
                    return this.left.searchParentNode(value);
                }else{
                    if (this.right == null){
                        return null;
                    }
                    return this.right.searchParentNode(value);
                }
            }
        }

        /**
         * 查找目标节点
         * @param value
         * @return
         */
        public Node searchTargetNode(T value) {
            if (value == null) {
                return null;
            }
            if (this.value == value) {
                return this;
            } else if (value.compareTo(this.value) < 0) {
                if (this.left == null) {
                    return null;
                }
                return this.left.searchTargetNode(value);
            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.searchTargetNode(value);
            }
        }
    }

}

