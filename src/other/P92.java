package other;

public class P92 {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n)
            return head;
        int count = 0;
        ListNode cNode = head;
        ListNode lNode = null;
        ListNode sNode = null;
        ListNode eNode = null;
        ListNode pNode = null;
        ListNode tNode = null;
        while (cNode != null) {
            tNode = cNode.next;
            ++count;
            if (count == m) {
                sNode = cNode;
                pNode = lNode;
            } else if (count > m && count <= n) {
                cNode.next = lNode;
                if (count == n) {
                    eNode = cNode;
                    break;
                }
            }
            lNode = cNode;
            cNode = tNode;
        }
        sNode.next = tNode;
        if (pNode == null)
            head = eNode;
        else
            pNode.next = eNode;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//        P92 p92 = new P92();
//        p92.reverseBetween(head, 1, 5);

        P92 p92 = new P92();
        p92.reverseBetween(head, 1, 1);

        System.out.println("===============END===============");
    }

}
