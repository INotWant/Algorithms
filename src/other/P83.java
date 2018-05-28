package other;

public class P83 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        ListNode tempNode;
        while (node != null) {
            int val = node.val;
            tempNode = node.next;
            while (tempNode != null && val == tempNode.val)
                tempNode = tempNode.next;
            node.next = tempNode;
            node = tempNode;
        }
        return head;
    }

}
