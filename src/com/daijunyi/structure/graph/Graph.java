package com.daijunyi.structure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author djy
 * @createTime 2022/7/26 12:54
 * @description 图-广度有限和深度优先，以及邻接矩阵的方式创建图
 */
public class Graph {

    /**
     * 存储顶点
     */
    private List<String> vertexList;

    /**
     * 存储图对应的邻结矩阵
     */
    private int[][] edges;

    /**
     * 边的个数
     */
    private Integer numOfEdges;

    public Graph(int size) {
        vertexList = new ArrayList<>(size);
        edges = new int[size][size];
        numOfEdges = 0;
    }

    /**
     * 插入订单数据
     * @param vertex
     */
    public void insertVertex(String vertex){
        this.vertexList.add(vertex);
    }

    /**
     * 设置边
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
