package other;

import java.util.*;

// 总结：不难，想难了
public class _leetcode_1_3 {

    // 超时
    // 对于 “距离” 的存储有问题，虽对 “距离” 做了排序但没做好
    /*
    private static class Node {
        private int pos;
        private int distance;

        Node(int pos, int distance) {
            this.pos = pos;
            this.distance = distance;
        }
    }

    @SuppressWarnings("unchecked")
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        List[] lists = new List[workers.length];
        for (int i = 0; i < lists.length; i++)
            lists[i] = new ArrayList();
        for (int i = 0; i < workers.length; i++) {
            int x1 = workers[i][0];
            int y1 = workers[i][1];
            for (int j = 0; j < bikes.length; j++) {
                int x2 = bikes[j][0];
                int y2 = bikes[j][1];
                int d = distance(x1, y1, x2, y2);
                lists[i].add(new Node(j, d));
            }
            lists[i].sort((o1, o2) -> {
                Node node1 = (Node) o1;
                Node node2 = (Node) o2;
                return Integer.compare(node1.distance, node2.distance);
            });
        }
        int[] result = new int[workers.length];
        Set<Integer> set1 = new HashSet();
        Set<Integer> set2 = new HashSet();
        for (int i = 0; i < workers.length; i++) {
            int min = Integer.MAX_VALUE;
            int pos = -1;
            int tJ = -1;
            for (int j = 0; j < lists.length; j++) {
                List tList = lists[j];
                if (!set2.contains(j))
                    for (Object obj : tList) {
                        Node node = (Node) obj;
                        if (!set1.contains(node.pos))
                            if (min > node.distance) {
                                min = node.distance;
                                pos = node.pos;
                                tJ = j;
                            }
                    }
            }
            result[tJ] = pos;
            set2.add(tJ);
            set1.add(pos);
        }
        return result;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    // */

    // 超超时
    // 思想：BFS + 集合收敛
    // 超时原因：对于搜过的点未去重(不能去重)
    /*
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] result = new int[workers.length];
        Map<String, Integer> workerIndexMap = new HashMap<>();
        for (int i = 0; i < workers.length; i++) {
            String key = workers[i][0] + "," + workers[i][1];
            workerIndexMap.put(key, i);
        }
        Map<Integer, Set<String>> bsMap = new HashMap<>();
        for (int i = 0; i < bikes.length; i++) {
            Set<String> set = new HashSet<>();
            set.add(bikes[i][0] + "," + bikes[i][1]);
            bsMap.put(i, getNext(set));
        }
        Set<Integer> bikeSet = new HashSet<>();
        Set<Integer> workerSet = new HashSet<>();
        while (workerSet.size() < workers.length) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < bikes.length; i++) {
                if (bikeSet.contains(i))
                    continue;
                Integer index = Integer.MAX_VALUE;
                Set<String> set = bsMap.get(i);
                for (String posStr : set) {
                    Integer currIndex = workerIndexMap.get(posStr);
                    if (currIndex != null && !workerSet.contains(currIndex) && currIndex < index)
                        index = currIndex;
                }
                if (index != Integer.MAX_VALUE) {
                    Integer bikeIndex = map.get(index);
                    if (bikeIndex == null) {
                        map.put(index, i);
                        bikeSet.add(i);
                        workerSet.add(index);
                    }
                }
                bsMap.put(i, getNext(set));
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet())
                result[entry.getKey()] = entry.getValue();
        }
        return result;
    }

    private Set<String> getNext(Set<String> currSet) {
        Set<String> nextSet = new HashSet<>();
        for (String str : currSet) {
            String[] split = str.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            String string;
            if (x - 1 >= 0) {
                string = (x - 1) + "," + y;
                nextSet.add(string);
            }
            string = (x + 1) + "," + y;
            nextSet.add(string);
            if (y - 1 >= 0) {
                string = x + "," + (y - 1);
                nextSet.add(string);
            }
            string = x + "," + (y + 1);
            nextSet.add(string);
        }
        return nextSet;
    }
    //*/

    // 通过
    //*
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        int[] ans = new int[n];
        boolean[] visw = new boolean[n];
        boolean[] visb = new boolean[m];
        Node[] list = new Node[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Node node = new Node(i, j,
                        Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]));
                list[i * m + j] = node;
            }
        }
        Comparator<Node> cmp = new MyComparator();
        Arrays.sort(list, cmp);
        int cnt = 0;
        for (int i = 0; i < n * m; i++) {
            if (!visw[list[i].id_worker] && !visb[list[i].id_bike]) {
                ans[list[i].id_worker] = list[i].id_bike;
                visw[list[i].id_worker] = true;
                visb[list[i].id_bike] = true;
                cnt++;
            }
            if (cnt == n)
                break;
        }
        return ans;
    }

    static class Node {
        int id_worker;
        int id_bike;
        int dis;

        Node(int id_worker, int id_bike, int dis) {
            this.id_worker = id_worker;
            this.id_bike = id_bike;
            this.dis = dis;
        }
    }

    static class MyComparator implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            if (a.dis == b.dis) {
                if (a.id_worker == b.id_worker)
                    return a.id_bike < b.id_bike ? -1 : 1;
                else
                    return a.id_worker < b.id_worker ? -1 : 1;
            } else
                return a.dis < b.dis ? -1 : 1;
        }
    }
    // */

    public static void main(String[] args) {

        int[][] workers = {{664, 994}, {3, 425}, {599, 913}, {220, 352}, {145, 348}, {604, 428}, {519, 183}, {732, 148}};
        int[][] bikes = {{611, 698}, {113, 338}, {579, 770}, {276, 588}, {948, 679}, {731, 525}, {925, 877}, {182, 281}, {39, 299}};

//        int[][] workers = {{0, 0}, {1, 1}, {2, 0}};
//        int[][] bikes = {{1, 0}, {2, 2}, {2, 1}};

        _leetcode_1_3 obj = new _leetcode_1_3();
        System.out.println(Arrays.toString(obj.assignBikes(workers, bikes)));
    }

}
