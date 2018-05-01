package other;

public class P61 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        int size = 0;
        ListNode cNode = head;
        if (head == null || head.next == null || k == 0)
            return head;
        while (cNode != null) {
            ++size;
            cNode = cNode.next;
        }
        k = k % size;
        cNode = head;
        ListNode lastHead = head;
        ListNode tempNode;
        for (int i = size; i >= 1; i--) {
            tempNode = cNode.next;
            if (i == k + 1) {
                cNode.next = null;
            } else if (i == k) {
                head = cNode;
                if (k == 1)
                    cNode.next = lastHead;
            } else if (i == 1) {
                cNode.next = lastHead;
            }
            cNode = tempNode;
        }
        return head;
    }

    public static void main(String[] args) {
        P61 p61 = new P61();
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        p61.rotateRight(head, 2);
    }

}
