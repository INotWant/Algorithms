package other;

/**
 * url :: https://leetcode.com/problems/merge-two-sorted-lists/description/
 *
 * @author kissx on 2018/2/12.
 */
public class P21 {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode ln1 = l1.val < l2.val ? l1 : l2;
        ListNode ln2 = l1.val >= l2.val ? l1 : l2;
        while (ln1 != null && ln2 != null) {
            if (ln1.next == null) {
                ln1.next = ln2;
                ln1 = null;
            } else if (ln1.next.val >= ln2.val) {
                ListNode lastN2 = ln2;
                while (ln2.next != null && ln2.next.val <= ln1.next.val)
                    ln2 = ln2.next;
                ListNode newN1 = ln1.next;
                ln1.next = lastN2;
                ln1 = ln2;
                ln2 = ln2.next;
                ln1.next = newN1;
                ln1 = newN1;
            } else
                ln1 = ln1.next;
        }
        return l1.val < l2.val ? l1 : l2;
    }

}
