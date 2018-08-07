package graph.minitree;

import graph.AdjacencyMatrix;

/**
 * @author Zhang
 * @date 2018/8/5
 * @Description      无向网图Prim算法
 */
public class Prim {

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

        MiniSpanTree_Prim();
    }

    //构建最小生成树
    private static void MiniSpanTree_Prim(){
        int min,i,j,k;

        int[] adjvex = new int[MAXVEX];

        int[] lowcost = new int[MAXVEX];

        lowcost[0] = 0;

        adjvex[0] = 0;

        for(i = 1; i < matrix.getVerNum(); i++){
            lowcost[i] = matrix.getArc()[0][i];
            adjvex[i] = 0;
        }

        for (i = 1; i < matrix.getVerNum(); i++){
            min = INFINITY;

            j = 1;
            k = 0;

            while (j < matrix.getVerNum()){
                if(lowcost[j] != 0 && lowcost[j] < min){
                    min = lowcost[j];
                    k = j;
                }

                j++;;
            }

            System.out.print(adjvex[k]+", "+k + " -> ");
            lowcost[k] = 0;

            for (j = 1; j < matrix.getVerNum(); j++){
                if(lowcost[j] != 0 && matrix.getArc()[k][j] < lowcost[j]){
                    lowcost[j] = matrix.getArc()[k][j];
                    adjvex[j] = k;
                }
            }
        }
    }
}
