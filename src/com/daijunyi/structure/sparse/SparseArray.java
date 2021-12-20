package com.daijunyi.structure.sparse;

import java.io.*;

/**
 * @author djy
 * @createTime 2021/12/19 上午7:20
 * @description
 */

public class SparseArray {

    public static void main(String[] args) {
        int[][] ints = new int[11][11];
        ints[1][2] = 1;
        ints[2][3] = 2;

        String filePath = "/Users/djy/Desktop/array.txt";
        try {
            int[][] spareArray = SparseArray.transitionSpareArray(ints);
            SparseArray.writeSpareArrayToFile(spareArray, filePath);
            int[][] readSpareArray = SparseArray.readSpareArrayFromFile(filePath);
            SparseArray.spareToNormalArray(readSpareArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 正常数组转稀疏数组
     * @param source 原始数组
     * @return 返回稀疏数组
     */
    public static int[][] transitionSpareArray(int[][] source) {

        int sum = 0;
        for (int[] row : source) {
            for (int data : row) {
                System.out.printf("%d\t", data);
                if (data != 0) {
                    sum++;
                }
            }
            System.out.println();
        }

        if (sum == 0) {
            return null;
        }
        System.out.println("稀疏数组打印:");
        //初始化稀疏数组
        int[][] spare = new int[sum + 1][3];
        spare[0][0] = source.length;
        spare[0][1] = source[0].length;
        spare[0][2] = sum;
        System.out.printf("%d\t%d\t%d\t", source.length, source[0].length, sum);
        System.out.println();

        //遍历赋值
        int count = 0;
        for (int row = 0; row < source.length; row++) {
            int[] rows = source[row];
            for (int col = 0; col < rows.length; col++) {
                int data = rows[col];
                if (data != 0) {
                    count++;
                    spare[count][0] = row;
                    spare[count][1] = col;
                    spare[count][2] = data;
                    System.out.printf("%d\t%d\t%d\t", row, col, data);
                    System.out.println();
                }
            }
        }
        return spare;
    }

    /**
     * 稀疏数组转正常数组
     * @param spareArray 稀疏数组
     * @return 正则数组
     */
    public static int[][] spareToNormalArray(int[][] spareArray) {
        if (spareArray == null || spareArray.length < 1) {
            return null;
        }
        int[][] normalArray = new int[spareArray[0][0]][spareArray[0][1]];
        for (int row = 1; row < spareArray.length; row++) {
            int[] rows = spareArray[row];
            normalArray[rows[0]][rows[1]] = rows[2];
        }

        System.out.println("打印转回来的数组");
        for (int[] row : normalArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        return normalArray;
    }

    /**
     * 从硬盘读取稀疏数组
     * @param path
     * @return
     * @throws IOException
     */
    public static int[][] readSpareArrayFromFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String value = null;
            int[][] spareArray = null;
            int index = 0;
            while ((value = bufferedReader.readLine()) != null) {
                if (index == 0){
                    spareArray = new int[Integer.valueOf(value)][3];
                }else{
                    String[] split = value.split(",");
                    if (split.length != 3){
                        throw new IOException("文件格式异常");
                    }
                    spareArray[index-1][0] = Integer.valueOf(split[0]);
                    spareArray[index-1][1] = Integer.valueOf(split[1]);
                    spareArray[index-1][2] = Integer.valueOf(split[2]);
                }
                index++;
            }
            return spareArray;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (bufferedReader != null){
                bufferedReader.close();
            }
        }
    }

    /**
     * 把数据写如硬盘
     * @param array
     * @param path
     * @throws IOException
     */
    public static void writeSpareArrayToFile(int[][] array, String path) throws IOException {
        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("数组为空");
        }
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("文件创建失败");
                throw e;
            }
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(array.length+"");
            fileWriter.write("\n");
            for (int[] rows : array) {
                String value = rows[0] + "," + rows[1] + "," + rows[2];
                fileWriter.write(value + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
}
