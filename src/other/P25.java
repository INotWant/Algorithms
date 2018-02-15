package other;

/**
 * url :: https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 *
 * @author kissx on 2018/2/15.
 */
public class P25 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int size = 0;
        ListNode node = head;
        while (node != null) {
            ++size;
            node = node.next;
        }
        if (k > size)
            return head;
        int count = size / k;
        ListNode lastNode = head;
        ListNode tempNode;
        // 1, process head so that return
        if (count >= 1) {
            node = head.next;
            for (int i = 1; i < k; i++) {
                tempNode = node.next;
                node.next = lastNode;
                lastNode = node;
                node = tempNode;
            }
            head.next = node;
            tempNode = head;
            head = lastNode;
            lastNode = tempNode;
            --count;
        }
        // 2, process others
        ListNode similarHead;
        for (int i = 0; i < count; i++) {
            similarHead = lastNode;
            lastNode = node;
            node = node.next;
            for (int j = 1; j < k; j++) {
                tempNode = node.next;
                node.next = lastNode;
                lastNode = node;
                node = tempNode;
            }
            similarHead.next.next = node;
            tempNode = similarHead.next;
            similarHead.next = lastNode;
            lastNode = tempNode;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        P25 p25 = new P25();
        ListNode head = p25.reverseKGroup(n1, 2);
        System.out.println(head);
    }

}
