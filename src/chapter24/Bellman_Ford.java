package chapter24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kissx on 2017/12/5.
 */
public class Bellman_Ford {

    // 顶点属性描述
    private static class Vertex {
        private String v;   // 顶点名称
        private int d;   // 到此顶点的距离
        private String lastV;   // 至该顶点的上个顶点

        public Vertex(String v, int d, String lastV) {
            this.v = v;
            this.d = d;
            this.lastV = lastV;
        }
    }

    /**
     * @param graph  有向图
     * @param vStart 源结点
     * @return 若存在负的环返回 NULL，否则返回 最短路径
     */
    public static List<String> bellmanFord(DirectedGraph graph, String vStart) {
        String[] vArray = graph.getvArray();
        Map<String, Vertex> vertexMap = new HashMap<>();
        for (String v : vArray)
            vertexMap.put(v, new Vertex(v, Integer.MAX_VALUE, null));
        vertexMap.put(vStart, new Vertex(vStart, 0, vStart));
        List<DirectedGraph.Edge> eArray = graph.geteArray();
        for (int i = 0; i < vArray.length - 1; i++) {
            for (DirectedGraph.Edge edge : eArray)
                if (!edge.v2.equals(vStart))
                    relax(edge, vertexMap);
        }
        for (DirectedGraph.Edge edge : eArray) {
            Vertex v1 = vertexMap.get(edge.v1);
            Vertex v2 = vertexMap.get(edge.v2);
            if (v1.d != Integer.MAX_VALUE && v1.d + edge.weight < v2.d)
                return null;    // 出现可到达的权重为负的环
        }
        List<String> resultList = new ArrayList<>();
        for (String v : vArray)
            resultList.add(getPath(vStart, v, vertexMap) + vertexMap.get(v).d);
        return resultList;
    }

    // 获取路径
    private static String getPath(String vStart, String v, Map<String, Vertex> vertexMap) {
        StringBuilder sb = new StringBuilder(v + ", ");
        Vertex vertex = vertexMap.get(v);
        while (!vertex.lastV.equals(vStart)) {
            sb.insert(0, vertex.lastV + ", ");
            vertex = vertexMap.get(vertex.lastV);
        }
        if (!v.equals(vStart))
            sb.insert(0, vStart + ", ");
        return sb.toString();
    }

    // 松弛操作
    private static void relax(DirectedGraph.Edge edge, Map<String, Vertex> vertexMap) {
        Vertex v1 = vertexMap.get(edge.v1);
        Vertex v2 = vertexMap.get(edge.v2);
        if (v1.d != Integer.MAX_VALUE) {
            if (v1.d + edge.weight < v2.d) {
                v2.d = v1.d + edge.weight;
                v2.lastV = v1.v;
            }
        }
    }

//    -------------------TEST-------------------

    public static void main(String[] args) {
        String[] vArray = {"s", "t", "x", "y", "z"};
        DirectedGraph.Edge[] eArray = new DirectedGraph.Edge[10];
        eArray[0] = new DirectedGraph.Edge("s", "t", 6);
        eArray[1] = new DirectedGraph.Edge("s", "y", 7);
        eArray[2] = new DirectedGraph.Edge("y", "z", 9);
        eArray[3] = new DirectedGraph.Edge("y", "x", -3);
        eArray[4] = new DirectedGraph.Edge("z", "x", 7);
        eArray[5] = new DirectedGraph.Edge("t", "z", -4);
        eArray[6] = new DirectedGraph.Edge("t", "x", 5);
        eArray[7] = new DirectedGraph.Edge("x", "t", -2);
        eArray[8] = new DirectedGraph.Edge("t", "y", 8);
        eArray[9] = new DirectedGraph.Edge("z", "s", 2);
        DirectedGraph graph = new DirectedGraph(vArray, eArray);
        List<String> result = Bellman_Ford.bellmanFord(graph, "s");
        if (result != null)
            for (String str : result)
                System.out.println(str);
    }

}
