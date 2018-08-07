package graph.engineering;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Zhang
 * @date 2018/8/7
 * @Description AOV网拓扑序列
 */
public class AOVNetwork {

    private static final int MAXVEX = 100;

    private static AdjList adjList = new AdjList();

    public static void main(String[] args) {
        CreateAdjList();
        if(! TopologicalSort())
            System.out.print("\n出现环路！");
    }

    //拓扑排序
    private static boolean TopologicalSort(){
        EdgeNode edgeNode = new EdgeNode();
        int i,k;
        int gettop = 0;
        int top = 0;          //栈指针下标
        int count = 0;        //输出顶点的个数
        Stack<Integer> stack = new Stack<>();
        for(i = 0; i < adjList.verNum; i++){
            if(adjList.vertexNodes[i].in == 0) {
                stack.push(i);
            }
        }

        while (! stack.empty()){

            gettop = stack.pop();

            System.out.print(adjList.vertexNodes[gettop].data+" -> ");
            count++;
            for(edgeNode = adjList.vertexNodes[gettop].firstEdge; edgeNode != null; edgeNode = edgeNode.next){
                k = edgeNode.adjvex;
                if(--adjList.vertexNodes[k].in == 0)
                    stack.push(k);
            }
        }

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
            System.out.println("请输入顶点下标i,j:");
            int i = in.nextInt();
            int j = in.nextInt();
            EdgeNode edgeNode = new EdgeNode();
            edgeNode.adjvex = j;
            edgeNode.next = adjList.vertexNodes[i].firstEdge;
            adjList.vertexNodes[i].firstEdge = edgeNode;
            adjList.vertexNodes[j].in++;
        }
    }

    //边表结点
    private static class EdgeNode{
        int adjvex;        //邻接点下标
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
