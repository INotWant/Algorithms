package other;

/**
 * LeetCode - 2
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * @author kissx on 2017/11/1.
 */


public class P2 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            boolean isJW = false;
            ListNode nl1 = l1;
            ListNode nl2 = l2;
            ListNode result = new ListNode(0);
            ListNode current = result;
            boolean isStart = true;
            while (nl1 != null || nl2 != null || isJW) {
                if (!isStart) {
                    ListNode last = new ListNode(0);
                    current.next = last;
                    current = last;
                }
                isStart = false;
                if (nl1 == null && nl2 != null) {
                    int temp = nl2.val;
                    if (isJW)
                        ++temp;
                    current.val = temp % 10;
                    isJW = temp / 10 == 1;
                    nl2 = nl2.next;
                } else if (nl2 == null && nl1 != null) {
                    int temp = nl1.val;
                    if (isJW)
                        ++temp;
                    current.val = temp % 10;
                    isJW = temp / 10 == 1;
                    nl1 = nl1.next;
                } else if (nl1 != null) {
                    int temp = nl1.val + nl2.val;
                    if (isJW)
                        ++temp;
                    current.val = temp % 10;
                    isJW = temp / 10 == 1;
                    nl1 = nl1.next;
                    nl2 = nl2.next;
                } else {
                    current.val = 1;
                    isJW = false;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
        ListNode result = solution.addTwoNumbers(l1, l2);
        System.out.println("----------- END -----------");
    }

}
