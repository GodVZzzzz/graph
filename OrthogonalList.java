package graph;

import java.util.Scanner;

/**
 * @author Zhang
 * @date 2018/8/4
 * @Description  十字链表
 */
/*输出示例：
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
    逆邻接表为：
    v0 <-- 2, 2 <-- 1, 9 <--
    v1 <--
    v2 <-- 1, 3 <--
    v3 <-- 2, 5 <--
    v4 <-- 0, 6 <-- 3, 1 <-- */
public class OrthogonalList {

    private static final int MAXVEX = 100;

    private static OrthogonalGraph orthogonalGraph = new OrthogonalGraph();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("请输入顶点数：");
        orthogonalGraph.VertexNum = in.nextInt();

        System.out.println("请输入边数：");
        orthogonalGraph.EdgeNum = in.nextInt();

        orthogonalGraph.vertexNodes = new VertexNode[MAXVEX];
        System.out.println("请输入顶点：");
        for(int i = 0; i < orthogonalGraph.VertexNum; i++){
            orthogonalGraph.vertexNodes[i] = new VertexNode(in.next());
        }

        for(int k = 0; k < orthogonalGraph.EdgeNum; k++){
            System.out.println("请输入顶点序号i,j和边上权重w：");
            int i = in.nextInt();
            int j = in.nextInt();
            int w = in.nextInt();
            EdgeNode edgeNode = new EdgeNode();
            edgeNode.weight = w;
            edgeNode.headvex = j;
            edgeNode.tailvex = i;
            edgeNode.taillink = orthogonalGraph.vertexNodes[i].firstOut;
            orthogonalGraph.vertexNodes[i].firstOut = edgeNode;

            edgeNode.headlink = orthogonalGraph.vertexNodes[j].firstin;
            orthogonalGraph.vertexNodes[j].firstin = edgeNode;
        }

        System.out.println("邻接表为：");
        for (int i = 0; i < orthogonalGraph.VertexNum; i++){
            System.out.print(orthogonalGraph.vertexNodes[i].data+" --> ");
            while (orthogonalGraph.vertexNodes[i].firstOut != null){
                System.out.print(orthogonalGraph.vertexNodes[i].firstOut.headvex +", "+orthogonalGraph.vertexNodes[i].firstOut.weight+"-->");
                orthogonalGraph.vertexNodes[i].firstOut = orthogonalGraph.vertexNodes[i].firstOut.taillink;
            }
            System.out.println();
        }

        System.out.println("逆邻接表为：");
        for (int i = 0; i < orthogonalGraph.VertexNum; i++){
            System.out.print(orthogonalGraph.vertexNodes[i].data+" <-- ");
            while (orthogonalGraph.vertexNodes[i].firstin != null){
                System.out.print(orthogonalGraph.vertexNodes[i].firstin.tailvex+", "+orthogonalGraph.vertexNodes[i].firstin.weight+" <-- ");
                orthogonalGraph.vertexNodes[i].firstin = orthogonalGraph.vertexNodes[i].firstin.headlink;
            }
            System.out.println();
        }
    }

    //边表结点
    private static class EdgeNode{
        int tailvex,headvex;            //邻接点域
        int weight;            //网图的权重
        EdgeNode headlink,taillink;          //出入结点
    }

    //顶点表结点
    private static class VertexNode{
        String data;           //顶点名称
        EdgeNode firstin;    //边表头入指针
        EdgeNode firstOut;   //边表头出指针

        VertexNode(String s){
            this.data = s;
            this.firstin = null;
            this.firstOut = null;
        }

    }

    //十字链表
    private static class OrthogonalGraph{
        VertexNode[] vertexNodes;       //存放顶点的数组
        int VertexNum,EdgeNum;         //顶点数和边数
    }

}
