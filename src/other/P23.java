package other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * url :: https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * @author kissx on 2018/2/13.
 */
public class P23 {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //* O(n*lg(n))
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> intList = new ArrayList<>();
        for (ListNode list : lists) {
            ListNode node = list;
            while (node != null) {
                intList.add(node.val);
                node = node.next;
            }
        }
        if (intList.size() == 0)
            return null;
        Integer[] ints = new Integer[intList.size()];
        intList.toArray(ints);
        Arrays.sort(ints);
        ListNode head = new ListNode(ints[0]);
        ListNode node = head;
        for (int i = 1; i < ints.length; i++) {
            ListNode tNode = new ListNode(ints[i]);
            node.next = tNode;
            node = tNode;
        }
        return head;
    }
    // */


    /* O(k * (n1 + n2 + n3 + ... + nm)) :: 本应该可以 Accepted ，不知道为什么 TTE
    // TODO ???
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode resultNode = lists[0];
        for (int i = 1; i < lists.length; i++)
            resultNode = mergeTwoLists(resultNode, lists[i]);
        return resultNode;
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
    // */

    public static void main(String[] args) {

        P23 p23 = new P23();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\project\\Algorithms\\data\\p23.txt"))) {
            String str = reader.readLine();
            String[] split = str.split("],\\[");
            ListNode[] listNodes = new ListNode[split.length];
            for (int i = 0; i < split.length; i++)
                listNodes[i] = new ListNode(Integer.parseInt(split[i]));
            long start = System.currentTimeMillis();
            ListNode resultNode = p23.mergeKLists(listNodes);
            long end = System.currentTimeMillis();
            while (resultNode != null) {
                System.out.println(resultNode.val);
                resultNode = resultNode.next;
            }
            System.out.println("Total Time :: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
