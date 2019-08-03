package other;

public class P203 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        while (head != null && head.val == val)
            head = head.next;
        if (head == null)
            return null;
        ListNode lastNode = null;
        ListNode currNode = head;
        while (currNode != null) {
            if (currNode.val == val)
                lastNode.next = currNode.next;
            else
                lastNode = currNode;
            currNode = currNode.next;
        }
        return head;
    }

}
