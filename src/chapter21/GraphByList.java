package chapter21;

import java.util.*;

/**
 * 图
 * [注]：前期错误地设计了图的邻接链表表示（把链表结点与顶点混了），导致 BFS 运行失败！！！
 *
 * @author kissx on 2017/11/22.
 */
public class GraphByList {

    private Map<String, eNode> eMap = new HashMap<>();  // 边集
    private String[] vArray;    // 顶点集
    private boolean haveDirection; // 是否是有向图
    private int time = 0;   // 用于 DFS 访问时间

    /**
     * 边的数据结构用于输入
     */
    public static class Edge {
        public final String v1; // 起点
        public final String v2; // 终点

        public Edge(String v1, String v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    // 标识顶点的状态（用于 BFS ）
    // white 开始时
    // gray 顶点被访问其邻接点过程中
    // black 顶点的邻接点访问结束时
    private enum MarkEnum {
        white, gray, black
    }

    /**
     * 用于 BFS 顶点描述
     */
    public static class BfsVertex {
        private String v;
        private MarkEnum mark = MarkEnum.white;
        private int distance;
        private BfsVertex pBfsVertex;

        private BfsVertex(String v) {
            this.v = v;
        }

        public String getV() {
            return v;
        }

        public int getDistance() {
            return distance;
        }

        public BfsVertex getpBfsVertex() {
            return pBfsVertex;
        }

        @Override
        public String toString() {
            return v + " :: " + distance;
        }
    }

    /**
     * 用于 DFS 顶点描述
     */
    public static class DfsVertex {
        private String v;
        private MarkEnum mark = MarkEnum.white;
        private int startTime;  // 初遇时间
        private int endTime;    // 访问结束时间
        private DfsVertex pDfsVertex;

        public DfsVertex(String v) {
            this.v = v;
        }

        public String getV() {
            return v;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public DfsVertex getpDfsVertex() {
            return pDfsVertex;
        }

        @Override
        public String toString() {
            return v + " :: " + startTime + "," + endTime;
        }
    }

    // 邻接矩阵中链表描述
    private static class eNode {
        private String v;   // 顶点（可以直接设计为引用 BfsVertex ，但是由于前期理解错误，没想到）
        private eNode nextENode;    // 下一个邻接点
    }

    /**
     * 输入边集、顶点集构建图（邻接矩阵方式）
     *
     * @param vArray        顶点集
     * @param edges         边集
     * @param haveDirection 判断是否是有向图
     */
    public GraphByList(String[] vArray, Edge[] edges, boolean haveDirection) {
        this.haveDirection = haveDirection;
        this.vArray = Arrays.copyOf(vArray, vArray.length);
        for (String v : vArray) {
            eMap.put(v, null);
        }
        if (edges != null) {
            for (Edge edge : edges) {
                eNode eNode1 = eMap.get(edge.v1);
                init(eNode1, edge.v1, edge.v2);
                if (!haveDirection) {
                    eNode eNode2 = eMap.get(edge.v2);
                    init(eNode2, edge.v2, edge.v1);
                }
            }
        }
    }

    // 初始化帮助方法
    private void init(eNode eNode, String v1, String v2) {
        if (eNode == null) {
            eNode n1 = new eNode();
            n1.v = v2;
            eMap.put(v1, n1);
        } else {
            eNode cENode = eNode;
            boolean flag = false;
            while (cENode.nextENode != null) {
                if (cENode.v.equals(v2)) {
                    flag = true;
                    break;
                }
                cENode = cENode.nextENode;
            }
            if (!flag) {
                eNode nENode = new eNode();
                nENode.v = v2;
                cENode.nextENode = nENode;
            }
        }
    }

    /**
     * BFS 遍历
     *
     * @param v 指定源顶点
     * @return 遍历顺序集合
     */
    public List<BfsVertex> bfs(String v) {
        Map<String, BfsVertex> bfsVMap = new HashMap<>();   // 用于 BFS 的顶点集
        for (String tempV : this.vArray)
            bfsVMap.put(tempV, new BfsVertex(tempV));
        return bfs(v, bfsVMap);
    }

    // 为了获取 bfsVMap 写的辅助方法（逻辑同上）
    // 想过把它放到类属性中，但是每次 bfs 前都需要修改
    private List<BfsVertex> bfs(String v, Map<String, BfsVertex> bfsVMap) {
        BfsVertex vertex = bfsVMap.get(v);
        List<BfsVertex> resultList = new ArrayList<>();
        resultList.add(vertex);

        LinkedList<BfsVertex> queue = new LinkedList<>();    // 中间变量（队列）
        queue.addFirst(vertex);
        bfsVMap.get(v).mark = MarkEnum.gray;
        while (!queue.isEmpty()) {
            vertex = queue.removeLast();
            eNode eNode = eMap.get(vertex.v);
            if (eNode != null) {
                eNode cENode = eNode;
                while (cENode != null) {
                    BfsVertex cVertex = bfsVMap.get(cENode.v);
                    if (cVertex.mark == MarkEnum.white) {
                        queue.addFirst(cVertex);
                        cVertex.mark = MarkEnum.gray;
                        cVertex.distance = vertex.distance + 1;
                        cVertex.pBfsVertex = vertex;
                        resultList.add(cVertex);
                    }
                    cENode = cENode.nextENode;
                }
                // 其邻接点都入队后（允许的），状态变为“黑色”
                vertex.mark = MarkEnum.black;
            }
        }
        return resultList;
    }

    /**
     * 使用 BFS 获取单源最短路径
     *
     * @param start 始顶点
     * @param end   终顶点
     * @return 最短路径（未考虑权重）
     */
    public String getShortPath(String start, String end) {
        if (start.equals(end))
            return start;
        StringBuilder sb = new StringBuilder();
        Map<String, BfsVertex> bfsVMap = new HashMap<>();   // 用于 BFS 的顶点集
        for (String tempV : this.vArray)
            bfsVMap.put(tempV, new BfsVertex(tempV));
        bfs(start, bfsVMap);
        if (bfsVMap.get(end) == null || bfsVMap.get(end).pBfsVertex == null)
            return "no path";
        getShortPathHelp(bfsVMap.get(end), sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 获取最短路径的递归辅助方法
    private void getShortPathHelp(BfsVertex bfsVertex, StringBuilder sb) {
        if (bfsVertex.pBfsVertex == null)
            sb.append(bfsVertex.v).append(",");
        else {
            getShortPathHelp(bfsVertex.pBfsVertex, sb);
            sb.append(bfsVertex.v).append(",");
        }
    }

    /**
     * 考虑多源情况的 DFS 遍历
     *
     * @return 遍历序列
     */
    public List<DfsVertex> dfs() {
        this.time = 0;  // 初始化为 0
        List<DfsVertex> resultList = new ArrayList<>();
        Map<String, DfsVertex> dfsVMap = new HashMap<>();
        for (String tempV : vArray)
            dfsVMap.put(tempV, new DfsVertex(tempV));
        for (Map.Entry<String, DfsVertex> temp : dfsVMap.entrySet()) {  // 考虑多源情况
            DfsVertex dfsVertex = temp.getValue();
            if (dfsVertex.mark == MarkEnum.white)
                dfsHelp(dfsVertex, dfsVMap, resultList);
        }
        return resultList;
    }

    // DFS 递归辅助方法
    private void dfsHelp(DfsVertex sVertex, Map<String, DfsVertex> dfsVMap, List<DfsVertex> resultList) {
        sVertex.startTime = ++time;
        sVertex.mark = MarkEnum.gray;
        eNode eNode = eMap.get(sVertex.v);
        while (eNode != null) {
            DfsVertex dfsVertex = dfsVMap.get(eNode.v);
            if (dfsVertex.mark == MarkEnum.white) {
                dfsVertex.pDfsVertex = sVertex; // 注意，理解深度优先树的构造过程
                dfsHelp(dfsVertex, dfsVMap, resultList);
            }
            eNode = eNode.nextENode;
        }
        sVertex.mark = MarkEnum.black;
        sVertex.endTime = ++time;
        resultList.add(sVertex);
    }

    public boolean isHaveDirection() {
        return haveDirection;
    }

    //    ---------------------- TEST ----------------------

    public static void main(String[] args) {
        // 1、testCrateGraph
        String[] vArray = {"r", "s", "t", "u", "v", "w", "x", "y"};

        // 边集1
//        Edge[] edges = new Edge[10];
//        edges[0] = new Edge("r", "s");
//        edges[1] = new Edge("r", "v");
//        edges[2] = new Edge("s", "w");
//        edges[3] = new Edge("w", "t");
//        edges[4] = new Edge("w", "x");
//        edges[5] = new Edge("t", "x");
//        edges[6] = new Edge("t", "u");
//        edges[7] = new Edge("x", "y");
//        edges[8] = new Edge("y", "u");
//        edges[9] = new Edge("x", "u");

        // 边集2
        Edge[] edges = new Edge[9];
        edges[0] = new Edge("r", "s");
        edges[1] = new Edge("r", "v");
        edges[2] = new Edge("w", "t");
        edges[3] = new Edge("w", "x");
        edges[4] = new Edge("t", "x");
        edges[5] = new Edge("t", "u");
        edges[6] = new Edge("x", "y");
        edges[7] = new Edge("y", "u");
        edges[8] = new Edge("x", "u");


        GraphByList graphByList = new GraphByList(vArray, edges, false);

//        GraphByList graphByList = new GraphByList(vArray, null, false);

        // 2、testBFS
        List<BfsVertex> resultList = graphByList.bfs("r");
        List<BfsVertex> resultList1 = graphByList.bfs("s");
        System.out.println(resultList);
        System.out.println(resultList1);

        // 3、testGetShortPath
        String shortPath = graphByList.getShortPath("r", "a");
        System.out.println(shortPath);

        // 4、testDFS

        List<DfsVertex> dfs = graphByList.dfs();
        System.out.println(dfs);

        System.out.println("--------------- END ---------------");
    }

}
