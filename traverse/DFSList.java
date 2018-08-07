package graph.traverse;

import graph.AdjacencyList;

/**
 * @author Zhang
 * @date 2018/8/5
 * @Description      //邻接表DFS示例
 */
public class DFSList {

    private static boolean[] visited = new boolean[100];

    private static AdjacencyList adjacencyList = new AdjacencyList();

    private static AdjacencyList.AdjacencyGraph lGraph = new AdjacencyList.AdjacencyGraph();

    public static void main(String[] args) {
        DFSTraverse();
    }

    //邻接表DFS算法
    private static void DFS(AdjacencyList.AdjacencyGraph graph, int i){
        AdjacencyList.EdgeNode edgeNode;
        visited[i] = true;
        System.out.print(graph.getVertexNodes()[i].getData());
        edgeNode = graph.getVertexNodes()[i].getFirstEdge();
        while (edgeNode != null){
            if(!visited[edgeNode.getAdjvex()]){
                DFS(graph, edgeNode.getAdjvex());
            }

            edgeNode = edgeNode.getNext();
        }
    }
    //邻接表深度遍历操作
    private static void DFSTraverse(){

        adjacencyList.CreateList();
        lGraph = adjacencyList.adjacencyGraph;
        int i;
        for(i = 0; i < lGraph.getVertexNum(); i++)
            visited[i] = false;

        for (i = 0; i < lGraph.getVertexNum(); i++){
            if(! visited[i]){
                DFS(lGraph, i);
            }
        }
    }
}
