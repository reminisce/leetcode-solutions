/**
 * Created on 6/4/16.
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 *
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode curOddNode = head;
        ListNode evenHead = head.next;
        ListNode curEvenNode = evenHead;

        while (curEvenNode != null && curEvenNode.next != null) {
            curOddNode.next = curEvenNode.next;
            curOddNode = curOddNode.next;
            curEvenNode.next = curOddNode.next;
            curEvenNode = curEvenNode.next;
        }
        curOddNode.next = evenHead;
        return head;
    }
}
