package com.daijunyi.structure.tree.avl;

class AVLTreeMain {
    public static void main(String[] args) {
        int[] arr = {10,11,7,6,8,9};
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i=0;i<arr.length;i++){
            tree.add(new AVLTree.Node(arr[i]));
        }
        tree.infixOrder();
        System.out.println("root节点:"+tree.getRoot().value);
        System.out.println("左节点:"+tree.getRoot().left.value);
        System.out.println("左左节点:"+tree.getRoot().left.left.value);
        System.out.println("右节点:"+tree.getRoot().right.value);
        System.out.println("右右节点:"+tree.getRoot().right.right.value);
        System.out.println("右左节点:"+tree.getRoot().right.left.value);
    }
}

/**
 * @author djy
 * @createTime 2022/7/16 09:14
 * @description 平衡二叉树
 */
public class AVLTree<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

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
    public Node searchParentNode(T value) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.searchParentNode(value);
        }
    }

    /**
     * 查找目标节点
     * @param value
     * @return
     */
    public Node searchTargetNode(T value) {
        if (this.root == null) {
            return null;
        } else {
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
            if (targetNode == null) {
                return null;
            }
            Node parentNode = this.searchParentNode(obj);
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode == null) {
                    //只有一个root节点，目标节点就是root节点的时候
                    this.root = null;
                    return targetNode;
                }
                //目标节点是叶子节点的时候
                if (parentNode.left != null && targetNode.value.compareTo(parentNode.left.value) == 0) {
                    parentNode.left = null;
                    return targetNode;
                } else if (parentNode.right != null && targetNode.value.compareTo(parentNode.right.value) == 0) {
                    parentNode.right = null;
                    return targetNode;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //左右两边都有节点的时候
                Node node = delRightMinNode(targetNode);
                node.left = targetNode.left;
                node.right = targetNode.right;
                if (parentNode == null) {
                    this.root = node;
                } else {
                    if (parentNode.left != null && parentNode.left.value.compareTo(targetNode.value) == 0) {
                        parentNode.left = node;
                    } else {
                        parentNode.right = node;
                    }
                }
                return targetNode;
            } else {
                if (parentNode == null) {
                    //目标节点就是root节点
                    //只有一边有节点的时候
                    if (targetNode.left != null) {
                        this.root = targetNode.left;
                        return targetNode;
                    } else {
                        this.root = targetNode.right;
                        return targetNode;
                    }
                } else {
                    if (targetNode.left != null) {
                        if (parentNode.left != null && parentNode.left.value.compareTo(targetNode.value) == 0) {
                            parentNode.left = targetNode.left;
                            return targetNode;
                        } else {
                            parentNode.right = targetNode.left;
                            return targetNode;
                        }
                    } else {
                        if (parentNode.left != null && parentNode.left.value.compareTo(targetNode.value) == 0) {
                            parentNode.left = targetNode.right;
                            return targetNode;
                        } else {
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
    public Node delRightMinNode(Node node) {
        Node del = node.right;
        while (del.left != null) {
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

            if (leftHeight() > rightHeight()){
                if (left != null && left.rightHeight() > left.leftHeight()){
                    left.leftRotate();
                }
                rightRotate();
            } else if (leftHeight() < rightHeight()){
                if (right != null && right.leftHeight() > right.rightHeight()){
                    right.rightRotate();
                }
                leftRotate();
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
            if (this.value == null) {
                return null;
            }
            if ((this.left != null && this.left.value.compareTo(value) == 0)
                    || (this.right != null && this.right.value.compareTo(value) == 0)) {
                return this;
            } else {
                if (value.compareTo(this.value) < 0) {
                    if (this.left == null) {
                        return null;
                    }
                    return this.left.searchParentNode(value);
                } else {
                    if (this.right == null) {
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

        /**
         * 左旋
         */
        public void leftRotate() {
            //1、创建新节点
            Node<T> newNode = new Node<>(this.value);
            //2、新节点的左节点等于当前节点的左节点
            newNode.left = left;
            //3、新节点的右节点等于当前节点的右节点的左节点
            newNode.right = right.left;
            //4、当前节点的右节点的值赋值给当前节点
            value = (T) right.value;
            //5、当前节点的左节点等于新节点
            left = newNode;
            //6、当前节点的右节点等于，右节点的右节点
            right = right.right;
        }

        public void rightRotate() {
            Node<T> newNode = new Node<>(this.value);
            newNode.right = right;
            newNode.left = left.right;
            value = (T) left.value;
            left = left.left;
            right = newNode;
        }

        public int leftHeight() {
            if (left == null) {
                return 0;
            }
            return left.height();
        }

        public int rightHeight() {
            if (right == null) {
                return 0;
            }
            return right.height();
        }

        public int height() {
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
        }
    }

}
