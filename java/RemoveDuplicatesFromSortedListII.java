import java.util.List;

/**
 * Created on 8/25/16.
 * Given a sorted linked list, delete all nodes
 * that have duplicate numbers, leaving only
 * distinct numbers from the original list.
 *
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */

public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null) {
            ListNode cur = pre.next;
            while (cur.next != null && cur.next.val == cur.val) cur = cur.next;
            if (cur != pre.next) pre.next = cur.next;
            else pre = pre.next;
        }

        ListNode newHead = dummyHead.next;
        dummyHead.next = null;
        return newHead;
    }
}
