package graph.shortestpath;

import graph.AdjacencyMatrix;

/**
 * @author Zhang
 * @date 2018/8/6
 * @Description 弗洛伊德算法
 */
public class Floyd {
    private static final int MAXVEX = 100;

    private static final int INFINITY = 65535;

    private static int[][] Pathmatirx = new int[MAXVEX][MAXVEX];

    private static int[][] ShortPathTable = new int[MAXVEX][MAXVEX];

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

        ShortestPath_Floyd();
    }

    //Floyd算法
    private static void ShortestPath_Floyd(){
        int v,w,k;
        for (v = 0; v < matrix.getVerNum(); ++v){
            for (w = 0; w < matrix.getVerNum(); ++w){
                Pathmatirx[v][w] = w;
                ShortPathTable[v][w] = matrix.getArc()[v][w];
            }
        }

        for (k = 0; k < matrix.getVerNum(); ++k){
            for (v = 0; v < matrix.getVerNum(); ++v){
                for(w = 0; w < matrix.getVerNum(); ++w){
                    if(ShortPathTable[v][w] > ShortPathTable[v][k]+ShortPathTable[k][w]){
                        ShortPathTable[v][w] = ShortPathTable[v][k]+ShortPathTable[k][w];
                        Pathmatirx[v][w]=Pathmatirx[v][k];
                    }
                }
            }
        }

        System.out.println("最短路径为："+ShortPathTable[0][matrix.getVerNum()-1]);
    }
}
