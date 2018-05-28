package other;

public class P82 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        ListNode lastNode = null;
        head = null;
        while (node != null) {
            int val = node.val;
            ListNode tempNode = node.next;
            int count = 1;
            while (tempNode != null && val == tempNode.val) {
                ++count;
                tempNode = tempNode.next;
            }
            if (count == 1) {
                if (lastNode == null) {
                    head = node;
                    lastNode = node;
                } else {
                    lastNode.next = node;
                    lastNode = node;
                }
                lastNode.next = null;
            }
            node = tempNode;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(2);
        P82 p82 = new P82();
        p82.deleteDuplicates(l1);
    }

}
