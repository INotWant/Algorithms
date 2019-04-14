package other;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class P146 {

    public static class LRUCache {

        private static LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = linkedHashMap.get(key);
            if (null != value) {
                linkedHashMap.remove(key);
                linkedHashMap.put(key, value);
                return value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (linkedHashMap.containsKey(key)) {
                linkedHashMap.remove(key);
                linkedHashMap.put(key, value);
            } else {
                if (linkedHashMap.size() == this.capacity) {
                    Integer oldKey = linkedHashMap.entrySet().iterator().next().getKey();
                    linkedHashMap.remove(oldKey);
                }
                linkedHashMap.put(key, value);
            }
        }
    }


    public static void main(String[] args) {
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(1);
        linkedHashSet.add(2);
        linkedHashSet.add(3);
//        linkedHashSet.remove(1);
        linkedHashSet.add(1);
//        System.out.println(linkedHashSet.iterator().next());
        for (Integer integer : linkedHashSet) {
            System.out.println(integer);
        }
    }

}
