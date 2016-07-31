/**
 * Created on 7/31/16.
 * Given a non-negative number represented
 * as a singly linked list of digits,
 * plus one to the number.
 *
 * The digits are stored such that the
 * most significant digit is at the head of the list.
 *
 * Example:
 * Input:
 * 1->2->3
 *
 * Output:
 * 1->2->4
 */

public class PlusOneLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(9);
        head.next = new ListNode(9);
        head.next.next = new ListNode(9);
        PlusOneLinkedList app = new PlusOneLinkedList();
        app.printList(head);
        head = app.plusOne(head);
        app.printList(head);
    }

    public ListNode plusOne(ListNode head) {
        if (null == head) return null;
        head = reverse(head);
        ListNode p = head;
        ListNode lastNode = p;
        int carry = 1;
        while (p != null) {
            p.val += carry;
            carry = p.val / 10;
            p.val %= 10;
            lastNode = p;
            p = p.next;
        }

        if (carry > 0) {
            lastNode.next = new ListNode(carry);
        }

        return reverse(head);
    }

    private ListNode reverse(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode lastNode = null;
        ListNode p = head;
        while (p != null) {
            ListNode nextNode = p.next;
            p.next = lastNode;
            lastNode = p;
            p = nextNode;
        }
        return lastNode;
    }

    private void printList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }
}
