package graph.traverse;

import graph.AdjacencyList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Zhang
 * @date 2018/8/5
 * @Description 邻接表BFS算法
 */
public class BFSList {
    private static boolean[] visited = new boolean[100];

    private static AdjacencyList adjacencyList = new AdjacencyList();

    private static AdjacencyList.AdjacencyGraph lGraph = new AdjacencyList.AdjacencyGraph();

    public static void main(String[] args) {
        adjacencyList.CreateList();
        lGraph = adjacencyList.adjacencyGraph;

        BFSTraverse();
    }

    //邻接表BFS算法
    private static void BFSTraverse(){
        int i;
        AdjacencyList.EdgeNode edgeNode;
        Queue<Integer> queue = new LinkedList<>();

        for(i = 0; i < lGraph.getVertexNum(); i++){
            visited[i] = false;
        }

        for (i = 0; i < lGraph.getVertexNum(); i++){
            if(! visited[i]){
                visited[i] = true;
                System.out.print(lGraph.getVertexNodes()[i].getData());
                queue.add(i);
                while (! queue.isEmpty()){
                    queue.poll();
                    edgeNode = lGraph.getVertexNodes()[i].getFirstEdge();
                    while (edgeNode != null) {
                        if(! visited[edgeNode.getAdjvex()]) {
                            visited[edgeNode.getAdjvex()] = true;
                            System.out.print(lGraph.getVertexNodes()[edgeNode.getAdjvex()].getData());
                            queue.poll();
                        }
                        edgeNode = edgeNode.getNext();
                    }
                }
            }
        }
    }
}
