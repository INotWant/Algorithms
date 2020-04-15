package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _leetcode_5_4 {


    // https://zxi.mytechroad.com/blog/algorithms/array/leetcode-1157-online-majority-element-in-subarray/
    // 方法一
    /*
    private static class MajorityChecker {

        private int[] arr;

        public MajorityChecker(int[] arr) {
            this.arr = arr;
        }

        private static class Node {
            int num;
            int count;

            public Node(int num, int count) {
                this.num = num;
                this.count = count;
            }
        }

        private Map<Integer, Node> saveMap = new HashMap<>();

        public int query(int left, int right, int threshold) {
            int key = left * 20000 + right;
            Node value = this.saveMap.get(key);
            if (value != null) {
                if (value.count >= threshold)
                    return value.num;
                else
                    return -1;
            }
            int[] tArr = new int[20001];
            int maxCount = 1;
            int v = arr[left];
            for (int i = left; i <= right; i++) {
                int count = tArr[arr[i]];
                tArr[arr[i]] = ++count;
                if (count > maxCount) {
                    maxCount = count;
                    v = arr[i];
                }
            }
            saveMap.put(key, new Node(v, maxCount));
            if (maxCount >= threshold)
                return v;
            return -1;
        }
    }
    // */

    // 方法二
    // TLE
    /*
    private static class MajorityChecker {

        private Map<Integer, List<Integer>> numPosMap = new HashMap<>();

        private List<Integer> sortList;

        public MajorityChecker(int[] arr) {
            int i = 0;
            for (int n : arr) {
                List<Integer> list = numPosMap.get(n);
                if (list == null)
                    list = new ArrayList<>();
                list.add(i);
                numPosMap.put(n, list);
                ++i;
            }
            sortList = new ArrayList<>(numPosMap.keySet());
            sortList.sort(Comparator.comparingInt(o -> numPosMap.get(o).size()));
        }

        public int query(int left, int right, int threshold) {
            for (int i = sortList.size() - 1; i >= 0; i--) {
                int n = sortList.get(i);
                int count = findUp(numPosMap.get(n), right) - findLow(numPosMap.get(n), left);
                if (count >= threshold)
                    return n;
            }
            return -1;
        }

        private int findUp(List<Integer> list, int x) {
            if (list == null || list.size() == 0)
                return 0;
            if (x >= list.get(list.size() - 1))
                return list.size();
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (list.get(mid) == x)
                    return mid + 1;
                else if (list.get(mid) < x)
                    i = mid + 1;
                else
                    j = mid;
            }
            return i;
        }

        private int findLow(List<Integer> list, int x) {
            if (list == null || list.size() == 0)
                return 0;
            if (x > list.get(list.size() - 1))
                return list.size();
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (list.get(mid) == x)
                    return mid;
                else if (list.get(mid) < x)
                    i = mid + 1;
                else
                    j = mid;
            }
            return i;
        }
    }
    // */

    // 方法三
    // 超时
    /*
    private static class MajorityChecker {

        private int[] arr;
        private Map<Integer, List<Integer>> numPosMap = new HashMap<>();

        public MajorityChecker(int[] arr) {
            this.arr = arr;
            int i = 0;
            for (int n : arr) {
                List<Integer> list = numPosMap.get(n);
                if (list == null)
                    list = new ArrayList<>();
                list.add(i);
                numPosMap.put(n, list);
                ++i;
            }
        }

        public int query(int left, int right, int threshold) {
            for (int i = left; i <= right; i++) {
                int n = this.arr[i];
                int count = findUp(numPosMap.get(n), right) - findLow(numPosMap.get(n), left);
                if (count >= threshold)
                    return n;
            }
            return -1;
        }

        private int findUp(List<Integer> list, int x) {
            if (list == null || list.size() == 0)
                return 0;
            if (x >= list.get(list.size() - 1))
                return list.size();
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (list.get(mid) == x)
                    return mid + 1;
                else if (list.get(mid) < x)
                    i = mid + 1;
                else
                    j = mid;
            }
            return i;
        }

        private int findLow(List<Integer> list, int x) {
            if (list == null || list.size() == 0)
                return 0;
            if (x > list.get(list.size() - 1))
                return list.size();
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (list.get(mid) == x)
                    return mid;
                else if (list.get(mid) < x)
                    i = mid + 1;
                else
                    j = mid;
            }
            return i;
        }
    }
     */

    // 方法四
    // 随机的巧妙应用！！！（但有很小概率出错）
    /*
    private static class MajorityChecker {

        private int[] arr;
        private Map<Integer, List<Integer>> numPosMap = new HashMap<>();

        private Random random = new Random();

        public MajorityChecker(int[] arr) {
            this.arr = arr;
            int i = 0;
            for (int n : arr) {
                List<Integer> list = numPosMap.get(n);
                if (list == null)
                    list = new ArrayList<>();
                list.add(i);
                numPosMap.put(n, list);
                ++i;
            }
        }

        public int query(int left, int right, int threshold) {
            for (int i = 0; i < 30; i++) {
                int n = this.arr[left + this.random.nextInt(right - left + 1)];
                int count = findUp(numPosMap.get(n), right) - findLow(numPosMap.get(n), left);
                if (count >= threshold)
                    return n;
            }
            return -1;
        }

        private int findUp(List<Integer> list, int x) {
            if (list == null || list.size() == 0)
                return 0;
            if (x >= list.get(list.size() - 1))
                return list.size();
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (list.get(mid) == x)
                    return mid + 1;
                else if (list.get(mid) < x)
                    i = mid + 1;
                else
                    j = mid;
            }
            return i;
        }

        private int findLow(List<Integer> list, int x) {
            if (list == null || list.size() == 0)
                return 0;
            if (x > list.get(list.size() - 1))
                return list.size();
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (list.get(mid) == x)
                    return mid;
                else if (list.get(mid) < x)
                    i = mid + 1;
                else
                    j = mid;
            }
            return i;
        }
    }
    // */

    // 线段树
    private static class MajorityChecker {

        private int[] arr;
        private int[] countArr;

        private Map<Integer, List<Integer>> numPosMap = new HashMap<>();

        public MajorityChecker(int[] arr) {
            int len = arr.length;
            int c = 2;
            while (c < len)
                c *= 2;
            c *= 2;
            this.arr = new int[c];
            this.countArr = new int[c];
            c /= 2;
            int temp = c;

            int pos = 0;
            for (int a : arr) {
                this.countArr[c] = 1;
                this.arr[c++] = a;

                List<Integer> list = numPosMap.get(a);
                if (list == null)
                    list = new ArrayList<>();
                list.add(pos);
                numPosMap.put(a, list);
                ++pos;
            }

            c = temp;
            while (c > 0) {
                for (int i = 0; i < c / 2; i++) {
                    int n1 = this.arr[c + i * 2];
                    int c1 = this.countArr[c + i * 2];

                    int n2 = this.arr[c + i * 2 + 1];
                    int c2 = this.countArr[c + i * 2 + 1];
                    if (n1 == 0) {
                        this.arr[c / 2 + i] = n2;
                        this.countArr[c / 2 + i] = c2;
                    } else if (n2 == 0) {
                        this.arr[c / 2 + i] = n1;
                        this.countArr[c / 2 + i] = c1;
                    } else if (n1 == n2) {
                        this.arr[c / 2 + i] = n1;
                        this.countArr[c / 2 + i] = c1 + c2;
                    } else {
                        if (c1 >= c2)
                            this.arr[c / 2 + i] = n1;
                        else
                            this.arr[c / 2 + i] = n2;
                        this.countArr[c / 2 + i] = Math.abs(c1 - c2);
                    }
                }
                c /= 2;
            }
        }

        public int query(int left, int right, int threshold) {
            List<Integer> list = new ArrayList<>();
            lineQueue(0, this.arr.length / 2 - 1, 1, left, right, list);
            int n = this.arr[list.get(0)];
            int c = this.countArr[list.get(0)];
            for (int i = 1; i < list.size(); i++) {
                int pos = list.get(i);
                int tn = this.arr[pos];
                int tc = this.countArr[pos];
                if (tn == n)
                    c += tc;
                else if (n == 0) {
                    n = tn;
                    c = tc;
                } else {
                    if (c < tc)
                        n = tn;
                    c = Math.abs(c - tc);
                }
            }


            int count = findUp(numPosMap.get(n), right) - findLow(numPosMap.get(n), left);
            if (count >= threshold)
                return n;
            return -1;
        }

        private void lineQueue(int start, int end, int pos, int i, int j, List<Integer> list) {
            if (i < start || j > end || i > j)
                return;
            if (start == i && end == j)
                list.add(pos);
            else {
                int mid = (start + end) / 2;
                lineQueue(start, mid, pos * 2, i, Math.min(j, mid), list);
                lineQueue(mid + 1, end, pos * 2 + 1, Math.max(mid + 1, i), j, list);
            }
        }

        private int findUp(List<Integer> list, int x) {
            if (list == null || list.size() == 0)
                return 0;
            if (x >= list.get(list.size() - 1))
                return list.size();
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (list.get(mid) == x)
                    return mid + 1;
                else if (list.get(mid) < x)
                    i = mid + 1;
                else
                    j = mid;
            }
            return i;
        }

        private int findLow(List<Integer> list, int x) {
            if (list == null || list.size() == 0)
                return 0;
            if (x > list.get(list.size() - 1))
                return list.size();
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (list.get(mid) == x)
                    return mid;
                else if (list.get(mid) < x)
                    i = mid + 1;
                else
                    j = mid;
            }
            return i;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 2, 1, 2, 2, 1, 1, 2};
        MajorityChecker obj = new MajorityChecker(nums);
        System.out.println(obj.query(6, 6, 1));
//        System.out.println(obj.query(0, 3, 3));
//        System.out.println(obj.query(2, 3, 2));
    }

}
