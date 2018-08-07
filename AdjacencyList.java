package graph;

import java.util.Scanner;

/**
 * @author Zhang
 * @date 2018/8/4
 * @Description      创建邻接表
 */
/*输入示例：
    请输入顶点数：
    5
    请输入边数：
    6
    请输入顶点：
    v0 v1 v2 v3 v4
    请输入顶点序号i,j和边上权重w：
    1 2 3
    请输入顶点序号i,j和边上权重w：
    1 0 9
    请输入顶点序号i,j和边上权重w：
    2 0 2
    请输入顶点序号i,j和边上权重w：
    2 3 5
    请输入顶点序号i,j和边上权重w：
    3 4 1
    请输入顶点序号i,j和边上权重w：
    0 4 6
    邻接表为：
    v0 --> 4, 6-->
    v1 --> 0, 9-->2, 3-->
    v2 --> 3, 5-->0, 2-->
    v3 --> 4, 1-->
    v4 -->
    */
public class AdjacencyList {

    private static final int MAXVEX = 100;

    public static AdjacencyGraph adjacencyGraph = new AdjacencyGraph();

    public static void main(String[] args) {

        System.out.println("邻接表为：");
        for (int i = 0; i < adjacencyGraph.VertexNum; i++){
            System.out.print(adjacencyGraph.vertexNodes[i].data+" --> ");
            while (adjacencyGraph.vertexNodes[i].firstEdge != null){
                System.out.print(adjacencyGraph.vertexNodes[i].firstEdge.adjvex +", "+adjacencyGraph.vertexNodes[i].firstEdge.weight+"-->");
                adjacencyGraph.vertexNodes[i].firstEdge = adjacencyGraph.vertexNodes[i].firstEdge.next;
            }
            System.out.println();
        }
    }

    public static void CreateList(){
        Scanner in = new Scanner(System.in);

        System.out.println("请输入顶点数：");
        adjacencyGraph.VertexNum = in.nextInt();

        System.out.println("请输入边数：");
        adjacencyGraph.EdgeNum = in.nextInt();

        adjacencyGraph.vertexNodes = new VertexNode[MAXVEX];
        System.out.println("请输入顶点：");
        for(int i = 0; i < adjacencyGraph.VertexNum; i++){
            adjacencyGraph.vertexNodes[i] = new VertexNode(in.next());
        }

        for(int k = 0; k < adjacencyGraph.EdgeNum; k++){
            System.out.println("请输入顶点序号i,j和边上权重w：");
            int i = in.nextInt();
            int j = in.nextInt();
            int w = in.nextInt();
            EdgeNode edgeNode = new EdgeNode();
            edgeNode.weight = w;
            edgeNode.adjvex = j;
            edgeNode.next = adjacencyGraph.vertexNodes[i].firstEdge;
            adjacencyGraph.vertexNodes[i].firstEdge = edgeNode;
        }
    }

    //边表结点
    public static class EdgeNode{
        int adjvex;            //邻接点域
        int weight;            //网图的权重
        EdgeNode next;          //next结点

        public EdgeNode getNext() {
            return next;
        }

        public int getAdjvex() {
            return adjvex;
        }

        public int getWeight() {
            return weight;
        }
    }

    //顶点表结点
    public static class VertexNode{
        String data;           //顶点名称
        EdgeNode firstEdge;    //边表头指针

        VertexNode(String s){
            this.data = s;
            this.firstEdge = null;
        }

        public String getData() {
            return data;
        }

        public EdgeNode getFirstEdge() {
            return firstEdge;
        }

    }

    //邻接表
    public static class AdjacencyGraph{
        VertexNode[] vertexNodes;       //存放顶点的数组
        int VertexNum,EdgeNum;         //顶点数和边数

        public int getEdgeNum() {
            return EdgeNum;
        }

        public int getVertexNum() {
            return VertexNum;
        }

        public VertexNode[] getVertexNodes() {
            return vertexNodes;
        }
    }
}
