package com.daijunyi.structure.huffmancode;

import java.util.*;

class HuffmanCodeMain {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] preBytes = content.getBytes();
        System.out.println("压缩前的数据" + Arrays.toString(preBytes));
        byte[] bytes = HuffmanCode.huffmanZip(preBytes);
        System.out.println("压缩后的数据" + Arrays.toString(bytes));
    }
}

/**
 * @author djy
 * @createTime 2022/5/19 下午3:21
 * @description 赫夫曼编码
 */
public class HuffmanCode {

    public static void huffmanUnzip(byte[] bytes) {

    }

    /**
     * 使用赫夫曼进行数据压缩
     * @param bytes
     * @return
     */
    public static byte[] huffmanZip(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("参数异常");
        }
        //生成赫夫曼树
        Node huffmanTree = getHuffmanTree(bytes);
        //生成赫夫曼编码
        Map<Byte, String> huffmanCode = getHuffmanCode(huffmanTree);
        //根据编码生成压缩后的数据
        return byteZip(bytes, huffmanCode);
    }

    /**
     * 根据赫夫曼编码表进行压缩字节
     * @param bytes
     * @param huffmanCode
     */
    private static byte[] byteZip(byte[] bytes, Map<Byte, String> huffmanCode) {
        StringBuilder zipString = new StringBuilder();
        for (byte b : bytes) {
            zipString.append(huffmanCode.get(b));
        }
        //新字节长度
        int len = (zipString.length() + 7) / 8;
        byte[] zipBytes = new byte[len];
        for (int i = 0; i < len; i++) {
            int startIndex = i * 8;
            String cutString = i == len - 1
                    ? zipString.substring(startIndex) : zipString.substring(startIndex, startIndex + 8);
            zipBytes[i] = (byte) Integer.parseInt(cutString, 2);
        }
        return zipBytes;
    }

    /**
     * 获取赫夫曼编码表
     * @param root 赫夫曼树根节点
     * @return
     */
    public static Map<Byte, String> getHuffmanCode(Node root) {
        HashMap<Byte, String> code = new HashMap<>();
        buildCode(code, root, "", new StringBuilder());
        return code;
    }

    /**
     * 递归构建赫夫曼编码
     * @param code
     * @param node
     * @param path
     * @param lastPath
     */
    public static void buildCode(HashMap<Byte, String> code, Node node, String path, StringBuilder lastPath) {
        if (node != null) {
            StringBuilder newString = new StringBuilder(lastPath).append(path);
            if (node.getData() == null) {
                //说明不是叶子节点
                buildCode(code, node.getLeft(), "0", newString);
                buildCode(code, node.getRight(), "1", newString);
            } else {
                code.put(node.getData(), newString.toString());
            }
        }
    }

    /**
     * 获取赫夫曼树
     * @param bytes 原始数据
     * @return
     */
    private static Node getHuffmanTree(byte[] bytes) {
        //找出出现的次数,并生成节点
        List<Node> nodes = getNodes(bytes);
        //根据出现的次数构建赫夫曼树
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    /**
     * 根据原始数据生成数据出现的次数节点
     * @param bytes
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> weights = new HashMap<>();
        for (byte b : bytes) {
            if (weights.get(b) == null) {
                weights.put(b, 1);
            } else {
                weights.put(b, weights.get(b) + 1);
            }
        }
        Set<Map.Entry<Byte, Integer>> entries = weights.entrySet();
        for (Map.Entry<Byte, Integer> entry : entries) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 赫夫曼的内部节点对象
     */
    static class Node implements Comparable<Node> {

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        private Byte data;
        private int weight;
        private Node left;
        private Node right;

        public int getWeight() {
            return weight;
        }

        public Byte getData() {
            return data;
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
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }
    }

}
