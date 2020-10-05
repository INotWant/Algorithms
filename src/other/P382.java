package other;

public class P382 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {

        private ListNode head;

        /**
         * @param head The linked list's head.
         *             Note that the head is guaranteed to be not null, so it contains at least one node.
         */
        public Solution(ListNode head) {
            this.head = head;
        }

        /**
         * Returns a random node's value.
         */
        public int getRandom() {
            ListNode node = this.head;
            int random = node.val;

            int count = 2;
            while (node.next != null) {
                node = node.next;
                if (Math.random() < 1.0 / count++)
                    random = node.val;
            }
            return random;
        }
    }


}
