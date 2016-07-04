/**
 * Created on 7/4/16.
 * A linked list is given such that each node contains an
 * additional random pointer which could point to any
 * node in the list or null.
 *
 * Return a deep copy of the list.
 */

public class CopyListWithRandomPointer {

    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(-1);
        head.next = head.random = null;

        CopyListWithRandomPointer app = new CopyListWithRandomPointer();
        RandomListNode head2 = app.copyRandomList(head);
        RandomListNode p = head2;
        while (p != null) {
            System.out.print(p.label + " ");
            p = p.next;
        }
        System.out.println();
    }

    private static class  RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (null == head) return null;
        RandomListNode head2 = new RandomListNode(head.label);
        RandomListNode p = head.next;
        head.next = head2;
        head2.next = p;
        while (p != null) {
            RandomListNode q = new RandomListNode(p.label);
            RandomListNode pnext = p.next;
            p.next = q;
            q.next = pnext;
            p = pnext;
        }

        p = head;
        while (p != null) {
            p.next.random = (p.random == null ? null : p.random.next);
            p = p.next.next;
        }

        p = head;
        while (p != null) {
            RandomListNode pnext = p.next.next;
            p.next.next = (pnext == null ? null : pnext.next);
            p.next = pnext;
            p = pnext;
        }

        return head2;
    }
}
