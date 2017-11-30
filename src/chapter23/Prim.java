package chapter23;

import chapter19.FIB_Heap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最小生成树 -- Prim 算法
 * 使用斐波那契堆
 *
 * @author kissx on 2017/11/30.
 */
public class Prim {

    // 用于路径存储
    private static class Data {
        private String eStart;
        private String eEnd;

        Data(String eStart, String eEnd) {
            this.eStart = eStart;
            this.eEnd = eEnd;
        }
    }

    public static UndirectedGraph.Edge[] getMinimumSpanningTree(UndirectedGraph graph) {
        List<UndirectedGraph.Edge> resultEdges = new ArrayList<>();
        FIB_Heap<Integer, Data> fib_heap = new FIB_Heap<>();
        String[] vArray = graph.getvArray();    // 获取点集
        Map<String, UndirectedGraph.ENode> eList = graph.getEList();    // 获取邻接链表
        Map<String, FIB_Heap.Node<Integer, Data>> nodeMap = new HashMap<>();    // 斐波那契堆的辅助 --> 用于存储结点
        for (String v : vArray) {   // 构建初始堆
            FIB_Heap.Node<Integer, Data> node = new FIB_Heap.Node<>(Integer.MAX_VALUE, new Data(null, v));
            nodeMap.put(v, node);
            fib_heap.insert(node);
        }
        fib_heap.decreaseKey(nodeMap.get(vArray[0]), 0);
        boolean isFirst = true;
        while (fib_heap.getNum() > 0) {
            FIB_Heap.Node<Integer, Data> cNode = fib_heap.extractMin(); // 抽取最小
            nodeMap.remove(cNode.getValue().eEnd);  // 去除
            if (!isFirst)
                resultEdges.add(new UndirectedGraph.Edge(cNode.getValue().eStart, cNode.getValue().eEnd, cNode.getKey()));    // 记录过程
            else
                isFirst = false;
            String eStrat = cNode.getValue().eEnd;   // 边起点
            UndirectedGraph.ENode eNode = eList.get(eStrat);
            while (eNode != null) { // 在添加新边后更新到堆中结点（剩余结点）的距离
                String eEnd = eNode.getV();     // 边终点
                FIB_Heap.Node<Integer, Data> tempNode = nodeMap.get(eEnd);
                if (tempNode != null) {
                    int newWeight = eNode.getWeight();
                    int oldWeight = tempNode.getKey();
                    if (newWeight < oldWeight) {
                        nodeMap.get(eEnd).getValue().eStart = eStrat;
                        fib_heap.decreaseKey(tempNode, newWeight);
                    }
                }
                eNode = eNode.getNextENode();
            }
        }
        return resultEdges.toArray(new UndirectedGraph.Edge[resultEdges.size()]);
    }

//    ----------------TEST----------------

    @Test
    public void test() {
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
        UndirectedGraph.Edge[] minimumSpanningTree = Prim.getMinimumSpanningTree(graph);
        for (UndirectedGraph.Edge edge : minimumSpanningTree)
            System.out.println(edge);
    }

}
