package graph.minitree;

import graph.AdjacencyMatrix;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Zhang
 * @date 2018/8/6
 * @Description
 */
public class Kruskal {
    private static AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();

    private static AdjacencyMatrix.Matrix matrix = new AdjacencyMatrix.Matrix();

    private static final int INFINITY = 65535;

    private static final int MAXVEX = 100;

    public static void main(String[] args) {
        adjacencyMatrix.CreateMGraph();

        matrix = adjacencyMatrix.matrix;

        for(int i = 0; i < matrix.getVerNum(); i++){
            for (int j = 0; j < matrix.getVerNum(); j++){
                if(matrix.getArc()[i][j] == INFINITY)
                    matrix.getArc()[i][j] = matrix.getArc()[j][i];
            }
        }

        MiniSpanTree_Kruskal();
    }

    //构造最小生成树
    private static void MiniSpanTree_Kruskal(){
        Edge[] edges = new Edge[matrix.getEdgeNum()];

        int i,n,m;
        int l = 0;

        int[] parent = new int[MAXVEX];   //判断是否发生回路

        for(i = 0; i < matrix.getVerNum(); i++){
            for(int j = i; j < matrix.getVerNum(); j++){
                if(matrix.getArc()[i][j] > 0 && matrix.getArc()[i][j] < INFINITY){
                    edges[l] = new Edge(i,j,matrix.getArc()[i][j]);
                    l++;
                }
            }
        }
        Arrays.sort(edges, Comparator.comparingInt(o -> o.weight));

        for (i = 0; i < matrix.getVerNum(); i++){
            parent[i] = 0;
        }

        for (i = 0; i < matrix.getEdgeNum(); i++){
            n = Find(parent, edges[i].start);
            m = Find(parent,edges[i].end);
            if(n != m){
                parent[n] = m;
                System.out.print(edges[i].start+", "+edges[i].end+", "+edges[i].weight+" -> ");
            }
        }
    }

    //查找连线顶点的尾部下标
    private static int Find(int[] parent, int f){
        while (parent[f] > 0)
            f = parent[f];
        return f;
    }

    //边集结点
    private static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

    }
}
