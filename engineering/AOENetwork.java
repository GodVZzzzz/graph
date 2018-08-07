package graph.engineering;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Zhang
 * @date 2018/8/7
 * @Description  AOE网求关键路径
 */
public class AOENetwork {

    private static final int MAXVEX = 100;

    private static AdjList adjList = new AdjList();

    private static int[] etv ;    //事件最早发生时间

    private static int[] ltv ;    //事件最迟发生时间

    private static Stack<Integer> stack2 = new Stack<>();    //存储拓扑序列

    public static void main(String[] args) {
        CreateAdjList();
        CriticalPath();
    }

    //求关键路径
    private static void CriticalPath(){
        ltv = new int[adjList.verNum];
        EdgeNode edgeNode = new EdgeNode();
        int i,gettop,j;
        int k = 0;
        int ete=0;
        int lte = 0;    //最早发生时间和最迟发生时间
        if(TopologicalSort()) {
            for (i = 0; i < adjList.verNum; i++) {
                ltv[i] = etv[adjList.verNum - 1];
            }

            while (!stack2.empty()) {
                gettop = stack2.pop();
                for (edgeNode = adjList.vertexNodes[gettop].firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
                    k = edgeNode.adjvex;
                    if (ltv[k] - edgeNode.weight < ltv[gettop]) {
                        ltv[gettop] = ltv[k] - edgeNode.weight;
                    }
                }
            }
            for (j = 0; j < adjList.verNum; j++) {
                for (edgeNode = adjList.vertexNodes[j].firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
                    k = edgeNode.adjvex;
                    ete = etv[j];
                    lte = ltv[k] - edgeNode.weight;

                    if (ete == lte) {
                        System.out.printf("<v%d, v%d> length: %d \n ", j,k, edgeNode.weight);
                    }
                }
            }

        }
    }

    //拓扑排序
    private static boolean TopologicalSort(){
        etv = new int[adjList.verNum];
        EdgeNode edgeNode = new EdgeNode();
        int i,k;
        int gettop = 0;
        int count = 0;        //输出顶点的个数
        Stack<Integer> stack = new Stack<>();
        for(i = 0; i < adjList.verNum; i++){
            if(adjList.vertexNodes[i].in == 0) {
                stack.push(i);
            }
        }

        for (i = 0; i < adjList.verNum; i++){
            etv[i] = 0;
        }


        System.out.println("拓扑序列为：");
        while (! stack.empty()){

            gettop = stack.pop();

            System.out.print(adjList.vertexNodes[gettop].data+" -> ");
            count++;
            stack2.push(gettop);           //   拓扑序列压栈
            for(edgeNode = adjList.vertexNodes[gettop].firstEdge; edgeNode != null; edgeNode = edgeNode.next){
                k = edgeNode.adjvex;
                if(--adjList.vertexNodes[k].in == 0)
                    stack.push(k);
                if(etv[gettop]+edgeNode.weight > etv[k]){                //求各顶点最早发生时间
                    etv[k] = etv[gettop] + edgeNode.weight;
                }
            }
        }
        System.out.println();

        return count >= adjList.verNum;
    }

    //创建邻接表
    private static void CreateAdjList(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入顶点数：");
        adjList.verNum = in.nextInt();
        System.out.println("请输入边数：");
        adjList.edgeNum = in.nextInt();
        System.out.println("请输入各个顶点：");
        adjList.vertexNodes = new VertexNode[MAXVEX];
        for (int i = 0; i < adjList.verNum; i++){
            adjList.vertexNodes[i] = new VertexNode();
            adjList.vertexNodes[i].data = in.next();
            adjList.vertexNodes[i].firstEdge = null;
            adjList.vertexNodes[i].in = 0;
        }

        for (int k = 0; k < adjList.edgeNum; k++) {
            System.out.println("请输入顶点下标i,j和权值w:");
            int i = in.nextInt();
            int j = in.nextInt();
            int w = in.nextInt();
            EdgeNode edgeNode = new EdgeNode();
            edgeNode.adjvex = j;
            edgeNode.weight = w;
            edgeNode.next = adjList.vertexNodes[i].firstEdge;
            adjList.vertexNodes[i].firstEdge = edgeNode;
            adjList.vertexNodes[j].in++;
        }
    }

    //边表结点
    private static class EdgeNode{
        int adjvex;        //邻接点下标
        int weight;        //权值
        EdgeNode next;     //下一个结点
    }

    //顶点表结点
    private static class VertexNode{
        int in;            //入度
        String data;          //顶点域
        EdgeNode firstEdge;  //边表头指针
    }

    //邻接表
    private static class AdjList{
        VertexNode[] vertexNodes;
        int verNum,edgeNum;
    }
}
