package other;

public class P148 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode split(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            ++len;
            node = node.next;
        }
        int i = 1;
        node = head;
        while (i < len / 2) {
            ++i;
            node = node.next;
        }
        ListNode resultNode = node.next;
        node.next = null;
        return resultNode;
    }

    private ListNode help(ListNode head) {
        if (null != head.next) {
            ListNode otherHead = split(head);
            ListNode p = help(head);
            ListNode q = help(otherHead);
            ListNode newHead = p.val <= q.val ? p : q;
            ListNode tNode;
            while (p != null && q != null) {
                if (p.val <= q.val) {
                    while (p.next != null && p.next.val <= q.val) {
                        p = p.next;
                    }
                    tNode = p.next;
                    p.next = q;
                    p = tNode;
                } else {
                    while (q.next != null && q.next.val < p.val) {
                        q = q.next;
                    }
                    tNode = q.next;
                    q.next = p;
                    q = tNode;
                }
            }
            return newHead;
        }
        return head;
    }

    public ListNode sortList(ListNode head) {
        if (null == head) {
            return null;
        }
        return help(head);
    }

    public static void main(String[] args) {

        // [4,19,14,5,-3,1,8,5,11,15]

        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(19);
        ListNode n3 = new ListNode(14);
        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(-3);
        ListNode n6 = new ListNode(1);
        ListNode n7 = new ListNode(8);
        ListNode n8 = new ListNode(5);
        ListNode n9 = new ListNode(11);
        ListNode n10 = new ListNode(15);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n8.next = n10;

        P148 p148 = new P148();
        ListNode listNode = p148.sortList(n1);
        System.out.println();
    }

}
