package graph;

import java.util.Scanner;

/**
 * @author Zhang
 * @date 2018/8/4
 * @Description    邻接多重表
 */
public class AdjacencyMultilistGragh {
    private static final int MAXVEX = 100;

    private static MultilistGraph multilistGraph = new MultilistGraph();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("请输入顶点数：");
        multilistGraph.VertexNum = in.nextInt();

        System.out.println("请输入边数：");
        multilistGraph.EdgeNum = in.nextInt();

        multilistGraph.vertexNodes = new VertexNode[MAXVEX];
        System.out.println("请输入顶点：");
        for (int i = 0; i < multilistGraph.VertexNum; i++) {
            multilistGraph.vertexNodes[i] = new VertexNode(in.next());
        }

        for (int k = 0; k < multilistGraph.EdgeNum; k++) {
            System.out.println("请输入顶点序号i,j：");
            int i = in.nextInt();
            int j = in.nextInt();
            EdgeNode edgeNode = new EdgeNode();
            edgeNode.ivex = i;
            edgeNode.jvex = j;
            edgeNode.ilink = multilistGraph.vertexNodes[i].firstEdge;
            edgeNode.jlink = multilistGraph.vertexNodes[j].firstEdge;
            multilistGraph.vertexNodes[i].firstEdge = edgeNode;
            multilistGraph.vertexNodes[j].firstEdge = edgeNode;
        }

        System.out.println("邻接表为：");
        for (int i = 0; i < multilistGraph.VertexNum; i++) {
            System.out.print(multilistGraph.vertexNodes[i].data + " --> ");
            while (multilistGraph.vertexNodes[i].firstEdge != null) {
                System.out.print(multilistGraph.vertexNodes[i].firstEdge.ivex + ", " + multilistGraph.vertexNodes[i].firstEdge.jvex+"-->");
                multilistGraph.vertexNodes[i].firstEdge = multilistGraph.vertexNodes[i].firstEdge.ilink;
            }
            System.out.println();
        }
    }

    //边表结点
    private static class EdgeNode {
        int ivex, jvex;            //邻接点域
        EdgeNode ilink, jlink;          //next结点
    }

    //顶点表结点
    private static class VertexNode {
        String data;           //顶点名称
        EdgeNode firstEdge;    //边表头指针

        VertexNode(String s) {
            this.data = s;
            this.firstEdge = null;
        }

    }

    //邻接表
    private static class MultilistGraph {
        VertexNode[] vertexNodes;       //存放顶点的数组
        int VertexNum, EdgeNum;         //顶点数和边数
    }
}
