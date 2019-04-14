package other;

public class P160 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 题意中的相交指的不仅仅是相等，就是同一个点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aSize = 0, bSize = 0;
        for (ListNode head = headA; head != null; head = head.next, aSize++) ;
        for (ListNode head = headB; head != null; head = head.next, bSize++) ;
        for (; aSize > bSize; aSize--, headA = headA.next) ;
        for (; bSize > aSize; bSize--, headB = headB.next) ;
        for (; headA != headB; headA = headA.next, headB = headB.next) ;
        return headA;
    }

}
