package chapter24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dijkstra 算法求单源最短路径
 *
 * @author kissx on 2017/12/5.
 */
public class Dijkstra {

    // 顶点属性描述
    private static class Vertex {
        private String v;   // 顶点名称
        private int d;   // 到此顶点的距离
        private String lastV;   // 至该顶点的上个顶点

        Vertex(String v, int d, String lastV) {
            this.v = v;
            this.d = d;
            this.lastV = lastV;
        }
    }

    /**
     * @param graph  权重为非负的有向图
     * @param vStart 开始结点
     * @return 返回至每个结点的最短路径
     */
    public static List<String> dijkstra(DirectedGraph graph, String vStart) {
        String[] vArray = graph.getvArray();
        Map<String, DirectedGraph.ENode> eNodeMap = graph.getEList();
        Map<String, Vertex> vertexMap = new HashMap<>();
        for (String v : vArray)
            vertexMap.put(v, new Vertex(v, Integer.MAX_VALUE, null));
        vertexMap.put(vStart, new Vertex(vStart, 0, vStart));
        Map<String, Vertex> resultMap = new HashMap<>();
        while (vertexMap.size() > 0) {
            String v = getMinVertex(vertexMap).v;
            DirectedGraph.ENode eNode = eNodeMap.get(v);
            while (eNode != null) {
                relax(new DirectedGraph.Edge(v, eNode.getV(), eNode.getWeight()), vertexMap);
                eNode = eNode.getNextENode();
            }
            resultMap.put(v, vertexMap.remove(v));
        }
        List<String> resultList = new ArrayList<>();
        for (String v : vArray)
            resultList.add(getPath(vStart, v, resultMap) + resultMap.get(v).d);
        return resultList;
    }

    // 使用数组维持最小队列（此处并没有使用斐波那契堆）
    // 寻找最小队列中的最小值
    private static Vertex getMinVertex(Map<String, Vertex> vertexMap) {
        int min = Integer.MAX_VALUE;
        Vertex minVertex = null;
        for (Map.Entry<String, Vertex> entry : vertexMap.entrySet()) {
            int d = entry.getValue().d;
            if (d <= min) {
                min = d;
                minVertex = entry.getValue();
            }
        }
        return minVertex;
    }

    // 松弛操作
    private static void relax(DirectedGraph.Edge edge, Map<String, Vertex> vertexMap) {
        Vertex v1 = vertexMap.get(edge.v1);
        Vertex v2 = vertexMap.get(edge.v2);
        if (v1.d != Integer.MAX_VALUE && v2 != null) {  // 注意此处对于以已经找到最短路径的顶点为终点的边不再执行松弛操作
            if (v1.d + edge.weight < v2.d) {
                v2.d = v1.d + edge.weight;
                v2.lastV = v1.v;
            }
        }
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

//    ---------------TEST---------------

    public static void main(String[] args) {
        String[] vArray = {"s", "t", "x", "y", "z"};
        DirectedGraph.Edge[] eArray = new DirectedGraph.Edge[10];
        eArray[0] = new DirectedGraph.Edge("s", "t", 10);
        eArray[1] = new DirectedGraph.Edge("s", "y", 5);
        eArray[2] = new DirectedGraph.Edge("t", "y", 2);
        eArray[3] = new DirectedGraph.Edge("y", "t", 3);
        eArray[4] = new DirectedGraph.Edge("y", "z", 2);
        eArray[5] = new DirectedGraph.Edge("z", "s", 7);
        eArray[6] = new DirectedGraph.Edge("y", "x", 9);
        eArray[7] = new DirectedGraph.Edge("z", "x", 6);
        eArray[8] = new DirectedGraph.Edge("x", "z", 4);
        eArray[9] = new DirectedGraph.Edge("t", "x", 1);
        DirectedGraph graph = new DirectedGraph(vArray, eArray);
        List<String> result = Dijkstra.dijkstra(graph, "s");
        if (result != null)
            for (String str : result)
                System.out.println(str);
    }

}
