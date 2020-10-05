package other;

import java.util.*;

class Node {
    int id;
    int val;

    Node(int id, int val) {
        this.id = id;
        this.val = val;
    }
}

public class _leetcode_7_3 {

    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();

        PriorityQueue<Node> q1 = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        PriorityQueue<Node> q2 = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        PriorityQueue<Node> q3 = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        int[] result = new int[requirements.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < requirements.length; i++) {
            if (requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0) {
                result[i] = 0;
                continue;
            }
            q1.add(new Node(i, requirements[i][0]));
            q2.add(new Node(i, requirements[i][1]));
            q3.add(new Node(i, requirements[i][2]));
        }

        int c = 0;
        int r = 0;
        int h = 0;

        for (int i = 0; i < increase.length; i++) {
            int currC = increase[i][0];
            int currR = increase[i][1];
            int currH = increase[i][2];
            c += currC;
            r += currR;
            h += currH;
            while (q1.size() > 0 && q1.peek().val <= c) {
                int id = q1.poll().id;
                set1.add(id);
                if (set2.contains(id) && set3.contains(id))
                    result[id] = i + 1;
            }
            while (q2.size() > 0 && q2.peek().val <= r) {
                int id = q2.poll().id;
                set2.add(id);
                if (set1.contains(id) && set3.contains(id))
                    result[id] = i + 1;
            }
            while (q3.size() > 0 && q3.peek().val <= h) {
                int id = q3.poll().id;
                set3.add(id);
                if (set1.contains(id) && set2.contains(id))
                    result[id] = i + 1;
            }
        }
        return result;
    }
}