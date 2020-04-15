package other;

import java.util.HashMap;
import java.util.Map;

public class _leetcode_4_3 {

    static class SnapshotArray {

        private int snapIndex = 0;
        private Map<Integer, Integer> map = new HashMap<>();
        private Map<Integer, Map<Integer, Integer>> snapMap = new HashMap<>();

        public SnapshotArray(int length) {
        }

        public void set(int index, int val) {
            map.put(index, val);
        }

        public int snap() {
            snapMap.put(snapIndex, new HashMap<>(this.map));
            return snapIndex++;
        }

        public int get(int index, int snap_id) {
            Integer val = snapMap.get(snap_id).get(index);
            if (val == null)
                return 0;
            return val;
        }
    }

    public static void main(String[] args) {
//          ["SnapshotArray","set","set","set","snap","get","snap"]
//          [[1],[0,4],[0,16],[0,13],[],[0,0],[]]

        SnapshotArray snapshotArray = new SnapshotArray(1);
        snapshotArray.set(0, 4);
        snapshotArray.set(0, 16);
        snapshotArray.set(0, 13);
        int snap = snapshotArray.snap();
        int val = snapshotArray.get(0, 0);
        System.out.println(val);
        snapshotArray.snap();
    }
}
