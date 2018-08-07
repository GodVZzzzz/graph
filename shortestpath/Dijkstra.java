package graph.shortestpath;

import graph.AdjacencyMatrix;

/**
 * @author Zhang
 * @date 2018/8/6
 * @Description 迪杰斯特拉算法
 */
public class Dijkstra {

    private static final int MAXVEX = 100;

    private static final int INFINITY = 65535;

    private static int[] Patharc = new int[MAXVEX];      //存储最短路径下标

    private static int[] ShortPathTable = new int[MAXVEX];    //存储到各点最短路径的下标和

    private static int k;

    private static AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();

    private static AdjacencyMatrix.Matrix matrix = new AdjacencyMatrix.Matrix();

    public static void main(String[] args) {

        adjacencyMatrix.CreateMGraph();

        matrix = adjacencyMatrix.matrix;

        for(int i = 0; i < matrix.getVerNum(); i++){
            for (int j = 0; j < matrix.getVerNum(); j++){
                if(matrix.getArc()[i][j] == INFINITY)
                    matrix.getArc()[i][j] = matrix.getArc()[j][i];
            }
        }

        ShortestPath_Dijkstra(0);

    }

    //迪杰斯特拉
    private static void ShortestPath_Dijkstra(int v0){
        int v, min;
        int w;
        int[] end =new int[matrix.getVerNum()];    //顶点至该点的路径长度

        for(v = 0; v < matrix.getVerNum(); v++){
            end[v] = 0;
            Patharc[v] = 0;                     //初始化路径数组都为0
            ShortPathTable[v] = matrix.getArc()[v0][v];      //给v0相邻的点加上权值
        }

        ShortPathTable[v0] = 0;
        end[v0] = 1;

        for(v = 1; v < matrix.getVerNum(); v++){
            min = INFINITY;       //当前所知离v0顶点最近的距离
            for(w = 0; w < matrix.getVerNum(); w++){
                if(end[w] == 0 && ShortPathTable[w] < min){
                    k = w;
                    min = ShortPathTable[w];
                }
            }
            end[k] = 1;
            for (w = 0; w < matrix.getVerNum(); w++){
                if(end[w] == 0 && (min + matrix.getArc()[k][w] < ShortPathTable[w])){
                    ShortPathTable[w] = min + matrix.getArc()[k][w];
                    Patharc[w] = k;
                }
            }

        }

        System.out.println("最短路径为："+ ShortPathTable[matrix.getVerNum()-1]);
    }

}
