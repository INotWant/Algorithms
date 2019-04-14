package other;

public class P147 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode newHead = head;
        ListNode node = head.next;
        head.next = null;
        while (node != null) {
            ListNode nextNode = node.next;
            ListNode tempNode = newHead;
            ListNode tempLastNode = null;
            while (tempNode != null && tempNode.val < node.val) {
                tempLastNode = tempNode;
                tempNode = tempNode.next;
            }
            if (tempNode == newHead) {
                newHead = node;
                node.next = tempNode;
            } else if (tempNode == null) {
                tempLastNode.next = node;
                node.next = null;
            } else {
                tempLastNode.next = node;
                node.next = tempNode;
            }
            node = nextNode;
        }
        return newHead;
    }

}
