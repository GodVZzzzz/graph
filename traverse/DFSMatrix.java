package graph.traverse;

import graph.AdjacencyMatrix;

/**
 * @author Zhang
 * @date 2018/8/5
 * @Description      邻接矩阵表示的图的深度优先遍历
 */
public class DFSMatrix {

    private static AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();

    private static AdjacencyMatrix.Matrix mGraph = new AdjacencyMatrix.Matrix();

    private static boolean visited[] = new boolean[100];                          //是否被访问的标志

    public static void main(String[] args) {
        DFSTraverse();
    }
    //邻接矩阵DFS算法
    private static void DFS(AdjacencyMatrix.Matrix matrix, int i){
        int j;
        visited[i] = true;
        System.out.print(matrix.getVexs()[i]);
        for (j = 0; j < matrix.getVerNum(); j++){
            if(matrix.getArc()[i][j] > 0 && ! visited[j]&&matrix.getArc()[i][j] != 65535 )
                DFS(matrix, j);
        }
    }
    //邻接矩阵深度遍历操作
    private static void DFSTraverse(){

        adjacencyMatrix.CreateMGraph();
        mGraph = adjacencyMatrix.matrix;
        int i;
        for (i = 0; i < mGraph.getVerNum(); i++){
            visited[i] = false;
        }

        for(i = 0; i < mGraph.getVerNum(); i++){
            if(! visited[i]){
                DFS(mGraph, i);
            }
        }
    }
}
