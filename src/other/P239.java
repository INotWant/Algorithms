package other;

import java.util.ArrayDeque;

public class P239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        DanDiaoDeque danDiaoDeque = new DanDiaoDeque(nums, k);
        for (int i = 0; i < nums.length; i++) {
            danDiaoDeque.enQueue(i);
            if (i >= k - 1)
                result[i - k + 1] = danDiaoDeque.getMax();
        }
        return result;
    }

    static class DanDiaoDeque {
        private int k;
        private int[] nums;
        private ArrayDeque<Integer> deque;

        public DanDiaoDeque(int[] nums, int k) {
            this.k = k;
            this.deque = new ArrayDeque<>(k);
            this.nums = nums;
        }

        public void enQueue(int index) {
            while (!this.deque.isEmpty() && this.nums[this.deque.getLast()] < this.nums[index])
                this.deque.removeLast();

            if (!this.deque.isEmpty() && index - this.deque.getFirst() >= this.k)
                this.deque.removeFirst();

            this.deque.addLast(index);
        }

        public int getMax() {
            return this.nums[this.deque.getFirst()];
        }
    }

}
