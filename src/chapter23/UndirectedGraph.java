package chapter23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 无向图
 *
 * @author kissx on 2017/11/30.
 */
public class UndirectedGraph {

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

    public UndirectedGraph(String[] vArray, Edge[] eArray) {
        this.vArray = vArray;
        this.eArray.addAll(Arrays.asList(eArray));
        Collections.sort(this.eArray);
    }

    public List<Edge> geteArray() {
        return eArray;
    }

    public String[] getvArray() {
        return vArray;
    }
}
