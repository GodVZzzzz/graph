package graph.traverse;

import graph.AdjacencyList;
import graph.AdjacencyMatrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Zhang
 * @date 2018/8/5
 * @Description      邻接矩阵BFS算法
 */
public class BFSMatrix {

    private static AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();

    private static AdjacencyMatrix.Matrix mGraph = new AdjacencyMatrix.Matrix();

    private static boolean visited[] = new boolean[100];                          //是否被访问的标志

    public static void main(String[] args) {
        adjacencyMatrix.CreateMGraph();
        mGraph = adjacencyMatrix.matrix;

        BFSTraverse();
    }

    //邻接矩阵BFS算法
    private static void BFSTraverse(){

        int i,j;
        Queue<Integer> queue = new LinkedList<>();
        for(i = 0; i < mGraph.getVerNum(); i++)
            visited[i] = false;

        for (i = 0; i < mGraph.getVerNum(); i++){
            if(! visited[i]){
                visited[i] = true;
                System.out.print(mGraph.getVexs()[i]);
                queue.add(i);
                while (! queue.isEmpty()){
                    queue.poll();
                    for (j = 0; j < mGraph.getVerNum(); j++){
                        if(mGraph.getArc()[i][j] > 0 && ! visited[j] && mGraph.getArc()[i][j] != 65535){
                            visited[j] = true;
                            System.out.print(mGraph.getVexs()[j]);
                            queue.poll();
                        }
                    }
                }
            }
        }
    }

}
