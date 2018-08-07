package graph;

import java.util.Scanner;

/**
 * @author Zhang
 * @date 2018/8/4
 * @Description          根据邻接矩阵建无向网图
 */
public class AdjacencyMatrix {

    private static final int INFINITY = 65535;   //用65535表示无穷大

    private static final int MAXVEX = 100;        //最大顶点数

    public static Matrix matrix = new Matrix();

    public static void main(String[] args) {

        CreateMGraph();
        for (int i = 0; i < matrix.verNum; i++) {
            Integer[] a = matrix.arc[i];
            for (int j = 0; j < matrix.verNum; j++) {
                if(a[j] == INFINITY)
                    System.out.print('\u221E'+", ");
                else
                    System.out.print(a[j] + ",  ");
            }

            System.out.println();
        }

    }


    public static void CreateMGraph(){
        Scanner in = new Scanner(System.in);

        System.out.println("请输入顶点数：");

        matrix.verNum = in.nextInt();

        System.out.println("请输入边数：");
        matrix.edgeNum = in.nextInt();

        matrix.vexs = new String[MAXVEX];
        matrix.arc = new Integer[MAXVEX][MAXVEX];

        System.out.println("请输入每个顶点：");
        for (int i = 0; i < matrix.verNum; i++) {
            matrix.vexs[i] = in.next();
        }

        for (int i = 0; i < matrix.verNum; i++) {
            for (int j = 0; j < matrix.verNum; j++) {
                matrix.arc[i][j] = INFINITY;
            }
        }

        for (int k = 0; k < matrix.edgeNum; k++) {
            System.out.println("请输入边上的下标i,j和权重w:");
            int i = in.nextInt();
            int j = in.nextInt();
            int w = in.nextInt();
            matrix.arc[i][j] = w;
        }

        for(int i = 0; i < matrix.verNum; i++){
            for(int j = 0; j < matrix.verNum; j++){
                if(i == j)
                    matrix.arc[i][j] = 0;
            }
        }
    }

    public static class Matrix{
        private String[] vexs;        //顶点数组
        private Integer[][] arc;      //邻接矩阵
        private int verNum,edgeNum;  //顶点数和边数

        public int getVerNum() {
            return verNum;
        }

        public Integer[][] getArc() {
            return arc;
        }

        public String[] getVexs() {
            return vexs;
        }

        public int getEdgeNum() {
            return edgeNum;
        }
    }
}
