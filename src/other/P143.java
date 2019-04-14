package other;

import java.util.ArrayList;
import java.util.List;

public class P143 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void reorderList(ListNode head) {
        if (null == head) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0;
        int j = list.size() - 1;
        for (; i < j; i++, j--) {
            ListNode node1 = list.get(i);
            ListNode node2 = list.get(j);
            node2.next = node1.next;
            node1.next = node2;
        }
        if (i == j) {
            list.get(j).next = null;
        } else {
            list.get(j + 1).next = null;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        P143 p143 = new P143();
        p143.reorderList(n1);
        System.out.println();
    }

}
