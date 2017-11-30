package chapter23;

import java.util.*;

/**
 * 无向图
 *
 * @author kissx on 2017/11/30.
 */
public class UndirectedGraph {

    private Map<String, ENode> eMap = new HashMap<>();  // 边集（邻接链表表示，用于Prim）
    private String[] vArray;    // 点集
    private List<Edge> eArray = new ArrayList<>();  // 边集

    /**
     * 边的数据结构
     */
    public static class Edge implements Comparable<Edge> {
        public final String v1; // 起点
        public final String v2; // 终点
        public final Integer weight;     // 权重

        public Edge(String v1, String v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight.compareTo(edge.weight);
        }

        @Override
        public String toString() {
            return "[ " + v1 + " : " + v2 + " , " + weight + " ]";
        }
    }

    // 邻接矩阵中链表描述
    public static class ENode {
        private String v;   // 顶点（可以直接设计为引用 BfsVertex ，但是由于前期理解错误，没想到）
        private Integer weight;
        private ENode nextENode;    // 下一个邻接点

        public String getV() {
            return v;
        }

        public Integer getWeight() {
            return weight;
        }

        public ENode getNextENode() {
            return nextENode;
        }
    }

    public UndirectedGraph(String[] vArray, Edge[] eArray) {
        this.vArray = vArray;
        this.eArray.addAll(Arrays.asList(eArray));
        Collections.sort(this.eArray);
        // 构建邻接链表
        for (Edge edge : eArray) {
            ENode ENode1 = eMap.get(edge.v1);
            init(ENode1, edge.v1, edge.v2, edge.weight);
            ENode eNode2 = eMap.get(edge.v2);
            init(eNode2, edge.v2, edge.v1, edge.weight);
        }
    }

    // 初始化帮助方法
    private void init(ENode ENode, String v1, String v2, int weight) {
        if (ENode == null) {
            ENode n1 = new ENode();
            n1.v = v2;
            n1.weight = weight;
            eMap.put(v1, n1);
        } else {
            ENode cENode = ENode;
            boolean flag = false;
            while (cENode.nextENode != null) {
                if (cENode.v.equals(v2)) {
                    flag = true;
                    break;
                }
                cENode = cENode.nextENode;
            }
            if (!flag) {
                ENode nENode = new ENode();
                nENode.v = v2;
                nENode.weight = weight;
                cENode.nextENode = nENode;
            }
        }
    }

    public List<Edge> geteArray() {
        return eArray;
    }

    public String[] getvArray() {
        return vArray;
    }

    public Map<String, ENode> getEList() {
        return eMap;
    }
}
