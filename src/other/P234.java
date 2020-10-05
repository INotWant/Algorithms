package other;

import chapter10.List;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;

public class P234 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;
        int total = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            ++total;
        }
        if (total == 1)
            return true;
        int mid = (total + 1) / 2 + 1;
        int i = 2;
        node = head.next;
        ListNode lastNode = head;
        while (i++ != mid) {
            ListNode tmp = node.next;
            node.next = lastNode;
            lastNode = node;
            node = tmp;
        }
        ListNode left;
        if (total % 2 == 0)
            left = lastNode;
        else
            left = lastNode.next;
        ListNode right = node;
        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode firstNode = new ListNode(1);
        firstNode.next = new ListNode(1);
//        firstNode.next = new ListNode(0);
//        firstNode.next.next = new ListNode(2);
//        firstNode.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(firstNode));
    }

}
