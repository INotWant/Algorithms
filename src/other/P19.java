package other;

/**
 * url :: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author kissx on 2018/2/11.
 */
public class P19 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null)
            return null;
        int size = 1;
        ListNode node = head.next;
        while (node != null) {
            ++size;
            node = node.next;
        }
        n = size - n + 1;
        if (n == 1)
            return head.next;
        node = head;
        for (int i = 1; i < n - 1; i++)
            node = node.next;
        node.next = node.next.next;
        return head;
    }

    public static void main(String[] args) {

    }
}
