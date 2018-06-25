package other;


public class P86 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode leftNode = null;
        ListNode leftCurrNode = null;
        ListNode rightNode = null;
        ListNode rightCurrNode = null;
        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                if (leftCurrNode == null) {
                    leftNode = new ListNode(node.val);
                    leftCurrNode = leftNode;
                } else {
                    leftCurrNode.next = new ListNode(node.val);
                    leftCurrNode = leftCurrNode.next;
                }
            } else {
                if (rightCurrNode == null) {
                    rightNode = new ListNode(node.val);
                    rightCurrNode = rightNode;
                } else {
                    rightCurrNode.next = new ListNode(node.val);
                    rightCurrNode = rightCurrNode.next;
                }
            }
            node = node.next;
        }
        if (leftCurrNode != null)
            leftCurrNode.next = rightNode;
        return leftNode == null ? rightNode : leftNode;
    }

}
