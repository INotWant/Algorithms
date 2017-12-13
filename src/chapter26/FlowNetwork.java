package chapter26;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kissx on 2017/12/13.
 */
public class FlowNetwork {

    private String sourceV; // 源点
    private String sinkV;   // 汇点
    private String[] vArray;    // 点集
    private Map<String, Edge> edges = new HashMap<>();    // 边集

    private Map<String, EdgeNode> map = new HashMap<>();   // 存储邻接链表（用于 BFS ）

    // 邻接链表元素结构
    public static class EdgeNode {
        private String endV;   // 终端节点
        private EdgeNode pre = null;
        private EdgeNode next = null;

        private EdgeNode(String endV) {
            this.endV = endV;
        }

        public String getEndV() {
            return endV;
        }

        public EdgeNode getNext() {
            return next;
        }

        public EdgeNode getPre() {
            return pre;
        }
    }

    // 边 数据结构
    public static class Edge {
        private String startV;
        private String endV;
        private int capacity;
        private int flow = 0;   // 流量初始为 0

        public Edge(String startV, String endV, int capacity) {
            this.startV = startV;
            this.endV = endV;
            this.capacity = capacity;
        }

        public String getStartV() {
            return startV;
        }

        public String getEndV() {
            return endV;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getFlow() {
            return flow;
        }

    }

    public FlowNetwork(Edge[] edges, String[] vArray, String sourceV, String sinkV) {
        this.vArray = vArray;
        this.sourceV = sourceV;
        this.sinkV = sinkV;
        for (Edge edge : edges) {
            // 1. 构造邻接矩阵
            String keyV = edge.startV;
            EdgeNode node = this.map.get(keyV);
            EdgeNode edgeNode = new EdgeNode(edge.endV);
            if (node == null) {
                this.map.put(keyV, edgeNode);
            } else {
                EdgeNode cNode = node;
                while (cNode.next != null)
                    cNode = cNode.next;
                cNode.next = edgeNode;
                edgeNode.pre = cNode;
            }
            // 2. 维护边集信息
            this.edges.put(edge.startV + "_" + edge.endV, edge);
        }

    }


    /**
     * 向邻接链表中删除一边
     *
     * @param startV 待删除边开始结点
     * @param endV   待删除边结束结点
     * @return 删除成功为 True 否则为 False （此边不存在）
     */
    private boolean deleteEdge(String startV, String endV) {
        EdgeNode node = this.map.get(startV);
        while (node != null) {
            if (node.endV.equals(endV)) {
                if (node.pre == null)
                    map.put(startV, node.next);
                else
                    node.pre.next = node.next;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    // 向邻接链表中增加一边
    private void addEdge(String startV, String endV) {
        EdgeNode node = this.map.get(startV);
        EdgeNode edgeNode = new EdgeNode(endV);
        if (node == null) {
            this.map.put(startV, edgeNode);
        } else {
            while (node.next != null)
                node = node.next;
            edgeNode.pre = node;
            node.next = edgeNode;
        }
    }

    /**
     * @return 返回当前流网络的流量
     */
    public int getFlowValue() {
        int flow = 0;
        for (Map.Entry<String, Edge> temp : edges.entrySet()) {
            Edge edge = temp.getValue();
            if (edge.startV.equals(sourceV))
                flow += edge.flow;
            if (edge.endV.equals(sourceV))
                flow -= edge.flow;
        }
        return flow;
    }

    public String[] getvArray() {
        return vArray;
    }

    /**
     * 修改 边属性 （流量属性）
     * 【Note】修改的过程主要是在维护 邻接链表
     *
     * @param flow 新流量
     */
    public boolean updateEdge(String startV, String endV, int flow) {
        Edge edge = this.edges.get(startV + "_" + endV);
        if (edge == null)
            return false;
        if (edge.capacity < flow || flow < 0)
            throw new RuntimeException("Need: 0 <= Flow <= Capacity");
        if (edge.flow == 0 && flow != 0)
            // 需要添加一边
            addEdge(endV, startV);
        if (flow == edge.capacity && edge.flow != edge.capacity)
            // 需要删除一边
            deleteEdge(startV, endV);
        edge.flow = flow;
        return true;
    }

    /**
     * @return 邻接链表
     */
    public Map<String, EdgeNode> getAdjacencyList() {
        return map;
    }

    public String getSinkV() {
        return sinkV;
    }

    public String getSourceV() {
        return sourceV;
    }

    public Map<String, Edge> getEdges() {
        return edges;
    }

    // 用于输出结果
    public void print() {
        for (Map.Entry<String, Edge> edge : edges.entrySet()) {
            String[] split = edge.getKey().split("_");
            if (edge.getValue().flow != 0) {
                System.out.println(split[0] + " -> " + split[1] + ", flow :: " + edge.getValue().flow);
            }
        }
    }
}
