package chapter25;

import chapter24.Bellman_Ford;
import chapter24.Dijkstra;
import chapter24.DirectedGraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kissx on 2017/12/10.
 */
public class Johnson {

    /**
     * Note：不允许存在结点名称为 “start”
     *
     * @param vArray 点集
     * @param eArray 边集
     * @return 所有结点对的最短路径；但，若存在负环返回 NULL
     */
    public static List<String> johnson(String[] vArray, DirectedGraph.Edge[] eArray) {
        // 要求结点名称不为 start
        for (String str : vArray)
            if (str.equals("start"))
                throw new RuntimeException("结点名称不允许为 start");
        // 1. 增加 “start” 结点
        String[] newVArray = new String[vArray.length + 1];
        DirectedGraph.Edge[] newEArray = new DirectedGraph.Edge[eArray.length + vArray.length];
        System.arraycopy(vArray, 0, newVArray, 0, vArray.length);
        newVArray[newVArray.length - 1] = "start";
        System.arraycopy(eArray, 0, newEArray, 0, eArray.length);
        for (int i = eArray.length; i < newEArray.length; i++)
            newEArray[i] = new DirectedGraph.Edge("start", vArray[i - eArray.length], 0);
        DirectedGraph directedGraph = new DirectedGraph(newVArray, newEArray);
        // 2. 调用 bellmanFored 算法，判断是否有负环，并且计算出由 start -> other 的最短路径
        List<String> bellmanFord = Bellman_Ford.bellmanFord(directedGraph, "start");    // 返回结果设计不是很好
        if (bellmanFord == null)
            return null;    // 有负环返回 NULL
        // 3. 重新计算权重，使其不为负值
        Map<String, Integer> hMap = new HashMap<>();
        for (String str : bellmanFord) {
            String[] split = str.split(", ");
            hMap.put(split[split.length - 2], Integer.valueOf(split[split.length - 1]));
        }
        for (int i = 0; i < eArray.length; i++) {
            DirectedGraph.Edge edge = eArray[i];
            int weight = edge.weight + hMap.get(edge.v1) - hMap.get(edge.v2);
            eArray[i] = new DirectedGraph.Edge(edge.v1, edge.v2, weight);
        }
        // 4. 调用 Dijkstra 计算最短路径
        List<String> result = new ArrayList<>();
        directedGraph = new DirectedGraph(vArray, eArray);
        for (String v : vArray) {     // Note: 由于 dijkstra 返回结果的结构设计的不好，导致在构造返回结果时效率不高。但此处不再修改！
            for (String temp : Dijkstra.dijkstra(directedGraph, v)) {
                String[] split = temp.split(", ");
                int lenght = Integer.parseInt(split[split.length - 1]) + hMap.get(split[split.length - 2]) - hMap.get(split[0]);
                result.add(temp.replace(split[split.length - 1], String.valueOf(lenght)));
            }
        }
        return result;
    }

//    --------------TEST--------------

    @Test
    public void test() {
        String[] vArray = {"a", "b", "c", "d", "e"};
        DirectedGraph.Edge[] eArray = new DirectedGraph.Edge[9];
        eArray[0] = new DirectedGraph.Edge("a", "b", 3);
        eArray[1] = new DirectedGraph.Edge("a", "e", -4);
        eArray[2] = new DirectedGraph.Edge("a", "c", 8);
        eArray[3] = new DirectedGraph.Edge("b", "d", 1);
        eArray[4] = new DirectedGraph.Edge("b", "e", 7);
        eArray[5] = new DirectedGraph.Edge("c", "b", 4);
        eArray[6] = new DirectedGraph.Edge("d", "a", 2);
        eArray[7] = new DirectedGraph.Edge("d", "c", -5);
        eArray[8] = new DirectedGraph.Edge("e", "d", 6);
        List<String> johnson = johnson(vArray, eArray);
        if (johnson != null)
            for (String str : johnson)
                System.out.println(str);
    }

}
