import java.util.List;

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
        ListNode head = new ListNode(1);
        head.next = new ListNode(9);
        head.next.next = new ListNode(9);
        PlusOneLinkedList app = new PlusOneLinkedList();
        app.printList(head);
        head = app.plusOne(head);
        app.printList(head);
    }

    /**
     * Another efficient solution is to find the first node that
     * is not 9. Add one to it and set the all 9's on its right
     * side to zero. If cannot find such a node, it means all
     * the nodes are 9. Just add a new node(1) before the head.
     * @param head
     * @return
     */
    public ListNode plusOne(ListNode head) {
        if (null == head) return null;
        ListNode p = null; // the first node that is not 9 from tail side
        ListNode cur = head;
        while (cur != null) {
            if (cur.val != 9) p = cur;
            cur = cur.next;
        }

        if (p == null) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            head = newHead;
            p = head;
        } else {
            ++p.val;
        }

        while (p.next != null) {
            p.next.val = 0;
            p = p.next;
        }

        return head;
    }



    public ListNode plusOne2(ListNode head) {
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
