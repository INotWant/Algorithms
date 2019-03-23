package other;

import java.util.HashSet;
import java.util.Set;

public class P142 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 方法1
    public ListNode detectCycle(ListNode head) {
        if (null == head) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            set.add(node);
            node = node.next;
        }
        return null;
    }

    // 方法2 --> 使用数学知识
    /*
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode p = head, p2 = head;
        boolean hasCycle = false;
        while (p2.next != null && p2.next.next != null) {
            p = p.next;
            p2 = p2.next.next;
            if (p == p2) {
                hasCycle = true;
                break;
            }
        }
        if (hasCycle) {
            ListNode q = head;
            while (p != q) {
                p = p.next;
                q = q.next;
            }
            return q;
        } else
            return null;
    }
    */

}
