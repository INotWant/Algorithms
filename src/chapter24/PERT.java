package chapter24;

import chapter22.GraphByList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Program Evaluation and Review Technique （计划评估与审查技术）
 * 注意：无健壮性
 *
 * @author kissx on 2017/12/5.
 */
public class PERT {

    private List<String> orderList;     // 记录拓扑排序
    private DirectedGraph graph;        // 记录有向无环图
    private Map<String, Vertex> vertexMap = new HashMap<>();    // 保存顶点属性

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
     * @param vArray 顶点集合
     * @param eArray 边集
     */
    public PERT(String[] vArray, DirectedGraph.Edge[] eArray) {
        GraphByList.Edge[] edges = new GraphByList.Edge[eArray.length];
        for (int i = 0; i < edges.length; i++)
            edges[i] = new GraphByList.Edge(eArray[i].v1, eArray[i].v2);
        this.orderList = new GraphByList(vArray, edges, true).topologicalOrdering();
        this.graph = new DirectedGraph(vArray, eArray);
    }

    /**
     * 求关键路径，关键路径为到该顶点的最长路径，表示完成此工作序列需要的最长时间
     */
    public List<String> getCriticalPath() {
        vertexMap.clear();
        String[] vArray = graph.getvArray();
        for (String v : vArray)
            vertexMap.put(v, new Vertex(v, Integer.MAX_VALUE, null));
        vertexMap.put(orderList.get(0), new Vertex(orderList.get(0), 0, orderList.get(0)));
        Map<String, DirectedGraph.ENode> eList = graph.getEList();
        for (int i = 0; i < orderList.size(); i++) {
            String v = orderList.get(i);
            DirectedGraph.ENode eNode = eList.get(v);
            while (eNode != null) {
                relax(new DirectedGraph.Edge(v, eNode.getV(), eNode.getWeight() * -1)); // 此处把权重都换为负值
                eNode = eNode.getNextENode();
            }
        }
        List<String> resultList = new ArrayList<>();
        for (String v : vArray)
            resultList.add(getPath(orderList.get(0), v) + " " + vertexMap.get(v).d * -1);
        return resultList;
    }

    // 松弛操作
    private void relax(DirectedGraph.Edge edge) {
        Vertex v1 = vertexMap.get(edge.v1);
        Vertex v2 = vertexMap.get(edge.v2);
        if (v1.d != Integer.MAX_VALUE) {
            if (v1.d + edge.weight < v2.d) {
                v2.d = v1.d + edge.weight;
                v2.lastV = v1.v;
            }
        }
    }

    // 获取路径
    private String getPath(String vStart, String v) {
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

//    -------------TEST-------------

    public static void main(String[] args) {
        String[] vArray = {"r", "s", "t", "x", "y", "z"};
        DirectedGraph.Edge[] eArray = new DirectedGraph.Edge[10];
        eArray[0] = new DirectedGraph.Edge("r", "s", 5);
        eArray[1] = new DirectedGraph.Edge("r", "t", 3);
        eArray[2] = new DirectedGraph.Edge("s", "t", 2);
        eArray[3] = new DirectedGraph.Edge("s", "x", 6);
        eArray[4] = new DirectedGraph.Edge("t", "x", 7);
        eArray[5] = new DirectedGraph.Edge("t", "y", 4);
        eArray[6] = new DirectedGraph.Edge("t", "z", 2);
        eArray[7] = new DirectedGraph.Edge("x", "y", 1);
        eArray[8] = new DirectedGraph.Edge("x", "z", 1);
        eArray[9] = new DirectedGraph.Edge("y", "z", 2);
        PERT pert = new PERT(vArray, eArray);
        List<String> result = pert.getCriticalPath();
        if (result != null)
            for (String str : result)
                System.out.println(str);
    }

}
