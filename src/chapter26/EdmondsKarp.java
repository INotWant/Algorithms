package chapter26;

import org.junit.Test;

import java.util.*;

/**
 * 基于 Ford_Fulkerson 方法使用 广度优先最短路径策略 寻找增广路径
 * 时间复杂度 O(V*E*E)
 *
 * @author kissx on 2017/12/13.
 */
public class EdmondsKarp {

    public static FlowNetwork edmonds_karp(FlowNetwork flowNetwork) {
        String sourceV = flowNetwork.getSourceV();
        String sinkV = flowNetwork.getSinkV();
        Map<String, FlowNetwork.EdgeNode> adjacencyList = flowNetwork.getAdjacencyList();   // 邻接矩阵
        boolean find;
        Map<String, String> path = new HashMap<>();
        do {
            find = false;
            // BFS 搜索 sourceV --> sinkV 的最短路径
            path.clear();
            LinkedList<String> queue = new LinkedList<>();
            Map<String, Boolean> accessedV = new HashMap<>();
            queue.addLast(sourceV);
            while (queue.size() > 0) {
                String v = queue.removeFirst();
                accessedV.put(v, true);
                if (sinkV.equals(v)) {
                    find = true;
                    break;
                }
                FlowNetwork.EdgeNode edgeNode = adjacencyList.get(v);
                while (edgeNode != null) {
                    String endV = edgeNode.getEndV();
                    if (accessedV.get(endV) == null) {
                        accessedV.put(endV, true);
                        queue.addLast(endV);
                        // 保存路径
                        path.put(endV, v);  // endV 的 parent 为 v
                    }
                    edgeNode = edgeNode.getNext();
                }
            }
            if (find) {
                // 更新边属性
                // 1. 查找路径，并计算出残存容量
                Map<String, FlowNetwork.Edge> edges = flowNetwork.getEdges();
                String parentV = sinkV;
                List<FlowNetwork.Edge> pathList = new ArrayList<>();
                Map<FlowNetwork.Edge, Boolean> edgeBooleanMap = new HashMap<>();  // 用来保存 缩小 边
                int vital = Integer.MAX_VALUE;
                while (!sourceV.equals(parentV)) {
                    String tempV = parentV;
                    parentV = path.get(parentV);
                    FlowNetwork.Edge edge = edges.get(parentV + "_" + tempV); // 这是增加的
                    if (edge != null) {
                        if (vital > edge.getCapacity() - edge.getFlow())
                            vital = edge.getCapacity() - edge.getFlow();
                    } else {
                        edge = edges.get(tempV + "_" + parentV); // 这是缩小的
                        edgeBooleanMap.put(edge, true);
                        if (vital > edge.getFlow())
                            vital = edge.getFlow();
                    }
                    pathList.add(edge);
                }
                // 2. 更新边属性
                for (FlowNetwork.Edge edge : pathList) {
                    if (edgeBooleanMap.get(edge) == null)  // 增加流量
                        flowNetwork.updateEdge(edge.getStartV(), edge.getEndV(), edge.getFlow() + vital);
                    else  // 缩小流量
                        flowNetwork.updateEdge(edge.getStartV(), edge.getEndV(), edge.getFlow() - vital);
                }
            }
        } while (find);
        return flowNetwork;
    }


//    -------------TEST-------------

    @Test
    public void test() {
        String[] vArray = {"s", "v1", "v2", "v3", "v4", "t"};
        FlowNetwork.Edge[] edges = new FlowNetwork.Edge[9];
        edges[0] = new FlowNetwork.Edge("s", "v1", 16);
        edges[1] = new FlowNetwork.Edge("s", "v2", 13);
        edges[2] = new FlowNetwork.Edge("v1", "v3", 12);
        edges[3] = new FlowNetwork.Edge("v2", "v1", 4);
        edges[4] = new FlowNetwork.Edge("v2", "v4", 14);
        edges[5] = new FlowNetwork.Edge("v3", "v2", 9);
        edges[6] = new FlowNetwork.Edge("v4", "v3", 7);
        edges[7] = new FlowNetwork.Edge("v3", "t", 20);
        edges[8] = new FlowNetwork.Edge("v4", "t", 4);
        FlowNetwork flowNetwork = new FlowNetwork(edges, vArray, "s", "t");
        edmonds_karp(flowNetwork);
        flowNetwork.print();
        System.out.println("---------END---------");
    }

}
