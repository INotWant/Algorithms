package other;

import java.util.PriorityQueue;

public class P703 {

    static class KthLargest {
        private PriorityQueue<Integer> queue;
        private int limit;

        public KthLargest(int k, int[] nums) {
            limit = k;
            queue = new PriorityQueue<>(k);
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (queue.size() < limit) {
                queue.add(val);
            } else if (val > queue.peek()) {
                queue.poll();
                queue.add(val);
            }

            return queue.peek();
        }
    }

    public static void main(String[] args) {
//        int[] arr = {4, 5, 8, 2};
//        KthLargest kthLargest = new KthLargest(3, arr);
//        kthLargest.add(3);
//        kthLargest.add(5);
//        kthLargest.add(10);
//        kthLargest.add(9);
//        kthLargest.add(4);

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(4);
        queue.add(3);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
    }

}
