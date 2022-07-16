package com.daijunyi.structure.huffmancode;

import java.io.*;
import java.util.*;

class HuffmanCodeMain {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] preBytes = content.getBytes();
        HuffmanCode.huffmanZipTest(preBytes);

        HuffmanCode.zipFile("/Users/djy/Desktop/2.doc","/Users/djy/Desktop/2.zip");
        HuffmanCode.unzipFile("/Users/djy/Desktop/2.zip","/Users/djy/Desktop/2jy.doc");
    }
}

/**
 * @author djy
 * @createTime 2022/5/19 下午3:21
 * @description 赫夫曼编码
 */
public class HuffmanCode {

    /**
     * 使用赫夫曼进行数据压缩
     * @param bytes
     * @return
     */
    public static void huffmanZipTest(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("参数异常");
        }
        System.out.println("压缩前的字符串数据:" + new String(bytes));
        System.out.println("压缩前的数据" + Arrays.toString(bytes));
        //生成赫夫曼树
        Node huffmanTree = getHuffmanTree(bytes);
        //生成赫夫曼编码
        Map<Byte, String> huffmanCode = getHuffmanCode(huffmanTree);
        //根据编码生成压缩后的数据
        byte[] zipBytes = byteZip(bytes, huffmanCode);
        System.out.println("压缩后的数据" + Arrays.toString(zipBytes));
        byte[] sourceBytes = byteUnzip(zipBytes, huffmanCode);
        System.out.println("解压后的数据" + Arrays.toString(sourceBytes));
        System.out.println("解压的字符串数据:" + new String(sourceBytes));
    }

    /**
     * 压缩文件
     * @param inputPath  输入文件
     * @param targetPath 输出文件
     */
    public static void zipFile(String inputPath, String targetPath) {
        InputStream io = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            io = new FileInputStream(new File(inputPath));
            byte[] b = new byte[io.available()];
            io.read(b);
            Node huffmanTree = getHuffmanTree(b);
            Map<Byte, String> huffmanCode = getHuffmanCode(huffmanTree);
            byte[] zipB = byteZip(b, huffmanCode);
            os = new FileOutputStream(new File(targetPath));
            oos = new ObjectOutputStream(os);
            oos.writeObject(zipB);
            oos.writeObject(huffmanCode);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                io.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩文件
     * @param inputPath
     * @param outPath
     */
    public static void unzipFile(String inputPath,String outPath){
        ObjectInputStream ois = null;
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(new File(inputPath));
            ois = new ObjectInputStream(is);
            byte[] data = (byte[]) ois.readObject();
            HashMap<Byte,String> code = (HashMap<Byte, String>) ois.readObject();
            byte[] bytes = byteUnzip(data, code);
            os = new FileOutputStream(new File(outPath));
            os.write(bytes);
            os.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩
     * @param bytes
     * @param code
     * @return
     */
    public static byte[] byteUnzip(byte[] bytes, Map<Byte, String> code) {
        //获取编码的字符串
        String codeStr = getCodeStr(bytes);
        //把编码表反向存储为key为编码，值为数据
        HashMap<String, Byte> reversedCode = getReversedHuffmanCode(code);
        byte[] sourceBytes = getSourceBytes(codeStr, reversedCode);
        return sourceBytes;
    }

    /**
     * 根据反向编码表获取原始byte数据
     * @param codeStr
     * @param reversedCode
     * @return
     */
    private static byte[] getSourceBytes(String codeStr, HashMap<String, Byte> reversedCode) {
        ArrayList<Byte> sourceArray = new ArrayList<>();
        //进行匹配
        for (int i = 0; i < codeStr.length(); ) {
            int count = 1;
            boolean find = false;
            Byte aByte = null;
            while (!find) {
                String subStr = codeStr.substring(i, i + count);
                aByte = reversedCode.get(subStr);
                if (aByte == null) {
                    count++;
                } else {
                    find = true;
                }
            }
            i += count;
            sourceArray.add(aByte);
        }

        byte[] sourceBytes = new byte[sourceArray.size()];
        for (int i = 0; i < sourceArray.size(); i++) {
            sourceBytes[i] = sourceArray.get(i);
        }
        return sourceBytes;
    }

    /**
     * 获取反向的编码表
     * @param code
     * @return
     */
    private static HashMap<String, Byte> getReversedHuffmanCode(Map<Byte, String> code) {
        HashMap<String, Byte> reversedCode = new HashMap<>();
        for (Map.Entry<Byte, String> entry : code.entrySet()) {
            reversedCode.put(entry.getValue(), entry.getKey());
        }
        return reversedCode;
    }

    /**
     * 把byte数据转换成编码的字符串
     * @param bytes
     */
    private static String getCodeStr(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        //先转会一个字符串
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i];
            if (i != bytes.length - 1) {
                //此处主要是为了 比如 int为 3 的时候 直接转成二进制字符串会是 11
                //256 二进制是 100000000 和11 |  成为 100000011
                b = b | 256;
            }
            String binStr = Integer.toBinaryString(b);
            if (i != bytes.length - 1) {
                binStr = binStr.substring(binStr.length() - 8);
            }
            stringBuilder.append(binStr);
        }
        return stringBuilder.toString();
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
        //根据新的字符串，转成新的字节数组
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
