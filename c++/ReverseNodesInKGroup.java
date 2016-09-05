/**
 * Created on 9/4/16.
 * Given a linked list, reverse the nodes of a
 * linked list k at a time and return its
 * modified list.
 *
 * If the number of nodes is not a multiple of
 * k then left-out nodes in the end should remain as it is.
 *
 * You may not alter the values in the
 * nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */

public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode p = head;
        for (int i = 2; i <= 10; ++i) {
            p.next = new ListNode(i);
            p = p.next;
        }
        ReverseNodesInKGroup app = new ReverseNodesInKGroup();
        app.printList(head);
        int k = 2;
        head = app.reverseKGroup(head, k);
        app.printList(head);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, cur = head;
        dummy.next = head;
        int i = 0;
        while (cur != null) {
            ++i;
            if (i % k == 0) {
                pre = reverseOneGroup(pre, cur.next);
                cur = pre.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * Reverse linked list from left.next to the node before right
     * @param left
     * @param right
     * @return
     */
    private ListNode reverseOneGroup(ListNode left, ListNode right) {
        ListNode tail = left.next;
        ListNode cur = left.next;
        ListNode last = right;
        while (cur != right) {
            ListNode next = cur.next;
            cur.next = last;
            last = cur;
            cur = next;
        }
        left.next = last;
        return tail;
    }

    private void printList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val);
            if (p.next != null) {
                System.out.print("->");
            }
            p = p.next;
        }
        System.out.println();
    }
}
