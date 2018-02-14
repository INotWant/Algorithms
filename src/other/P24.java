package other;

/**
 * url :: https://leetcode.com/problems/swap-nodes-in-pairs/description/
 *
 * @author kissx on 2018/2/14.
 */
public class P24 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // 1. the first is special
        ListNode node = head;
        head = head.next;
        node.next = head.next;
        head.next = node;
        ListNode lastNode = node;
        node = node.next;
        ListNode nextNode;
        // 2. other
        while (node != null) {
            if (node.next != null) {
                nextNode = node.next;
                lastNode.next = nextNode;
                node.next = nextNode.next;
                nextNode.next = node;
                lastNode = node;
                node = node.next;
            } else
                node = null;
        }
        return head;
    }

}
