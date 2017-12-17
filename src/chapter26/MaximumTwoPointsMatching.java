package chapter26;

import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最大二分匹配
 *
 * @author kissx on 2017/12/17.
 */
public class MaximumTwoPointsMatching {

    public static class BinaryPictureEdge {
        public final String startV;     // 始结点名称，或看成 left
        public final String endV;       // 终结点名称，或看成 right

        public BinaryPictureEdge(String startV, String endV) {
            this.startV = startV;
            this.endV = endV;
        }
    }


    /**
     * 求最大二分匹配
     * Note：“source” “sink” 为保留字，结点名称不能为此！
     *
     * @param edges 二分图
     * @return 最大二分图大小
     */
    public static int maximumTwoPointsMatching(BinaryPictureEdge[] edges) {
        Set<String> vSet = new HashSet<>();
        Set<String> vStartSet = new HashSet<>();
        Set<String> vEndSet = new HashSet<>();
        for (BinaryPictureEdge edge : edges) {
            vStartSet.add(edge.startV);
            vEndSet.add(edge.endV);
        }
        vSet.addAll(vStartSet);
        vSet.addAll(vEndSet);
        // 0.1 构造 vArray，为 new FlowNetwork 做准备
        String[] vArray = new String[vSet.size() + 2];
        int i = 0;
        for (String v : vSet)
            vArray[i++] = v;
        vArray[i++] = "source";
        vArray[i] = "sink";
        // 0.2 构造 netEdges，为 new FlowNetwork 做准备
        FlowNetwork.Edge[] netEdges = new FlowNetwork.Edge[edges.length + vSet.size()];
        for (int j = 0; j < edges.length; j++)
            netEdges[j] = new FlowNetwork.Edge(edges[j].startV, edges[j].endV, 1);
        i = edges.length;
        for (String v : vStartSet)
            netEdges[i++] = new FlowNetwork.Edge("source", v, 1);
        for (String v : vEndSet)
            netEdges[i++] = new FlowNetwork.Edge(v, "sink", 1);
        // 1 new FlowNetwork
        FlowNetwork flowNetwork = new FlowNetwork(netEdges, vArray, "source", "sink");
        // 2 调用 edmonds_karp 算法
        Map<String, FlowNetwork.Edge> edgeMap = EdmondsKarp.edmonds_karp(flowNetwork).getEdges();
        // 3 返回大小
        int size = 0;
        for (Map.Entry<String, FlowNetwork.Edge> edge : edgeMap.entrySet()) {
            if (edge.getValue().getFlow() != 0)
                ++size;
        }
//        flowNetwork.print();
        return size / 3;
    }


//    -----------------TEST-----------------

    @Test
    public void test() {
        BinaryPictureEdge[] edges = new BinaryPictureEdge[8];
        edges[0] = new BinaryPictureEdge("1", "6");
        edges[1] = new BinaryPictureEdge("2", "6");
        edges[2] = new BinaryPictureEdge("2", "8");
        edges[3] = new BinaryPictureEdge("3", "7");
        edges[4] = new BinaryPictureEdge("3", "8");
        edges[5] = new BinaryPictureEdge("3", "9");
        edges[6] = new BinaryPictureEdge("4", "8");
        edges[7] = new BinaryPictureEdge("5", "8");
        int size = maximumTwoPointsMatching(edges);
        System.out.println("最大匹配的基数为 :: " + size);
    }

}
