package chapter23;

import chapter21.DisjointSetByTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小生成树 --> Kruskal
 *
 * @author kissx on 2017/11/30.
 */
public class Kruskal {

    public static UndirectedGraph.Edge[] getMinimumSpanningTree(UndirectedGraph graph) {
        List<UndirectedGraph.Edge> resultEdges = new ArrayList<>();
        DisjointSetByTree<String> disjointSetByTree = new DisjointSetByTree<>();
        String[] vArray = graph.getvArray();
        for (String v : vArray)     // 构建“初始森林”
            disjointSetByTree.makeSet(v);
        List<UndirectedGraph.Edge> edges = graph.geteArray();   // 边集已经按照降序排好
        for (UndirectedGraph.Edge e : edges) {  // 依边权重从小到大遍历边集
            String v1 = e.v1;
            String v2 = e.v2;
            if (!disjointSetByTree.findSet(v1).equals(disjointSetByTree.findSet(v2))) { // 判断是否是连通不同“强连通树”的边
                resultEdges.add(e);
                disjointSetByTree.union(v1, v2);    // 合并两棵树
            }
        }
        return resultEdges.toArray(new UndirectedGraph.Edge[resultEdges.size()]);
    }


//    -------------------TEST-------------------

    @Test
    public void testKruskal() {
        String[] vArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        UndirectedGraph.Edge[] eArray = new UndirectedGraph.Edge[14];
        eArray[0] = new UndirectedGraph.Edge("a", "b", 4);
        eArray[1] = new UndirectedGraph.Edge("a", "h", 8);
        eArray[2] = new UndirectedGraph.Edge("b", "h", 11);
        eArray[3] = new UndirectedGraph.Edge("h", "i", 7);
        eArray[4] = new UndirectedGraph.Edge("h", "g", 1);
        eArray[5] = new UndirectedGraph.Edge("i", "g", 6);
        eArray[6] = new UndirectedGraph.Edge("g", "f", 2);
        eArray[7] = new UndirectedGraph.Edge("c", "f", 4);
        eArray[8] = new UndirectedGraph.Edge("c", "i", 2);
        eArray[9] = new UndirectedGraph.Edge("b", "c", 8);
        eArray[10] = new UndirectedGraph.Edge("c", "d", 7);
        eArray[11] = new UndirectedGraph.Edge("d", "f", 14);
        eArray[12] = new UndirectedGraph.Edge("e", "f", 10);
        eArray[13] = new UndirectedGraph.Edge("d", "e", 9);
        UndirectedGraph graph = new UndirectedGraph(vArray, eArray);
        UndirectedGraph.Edge[] minimumSpanningTree = Kruskal.getMinimumSpanningTree(graph);
        for (UndirectedGraph.Edge edge : minimumSpanningTree)
            System.out.println(edge);
    }
}
