import java.util.Random;

/**
 * Created on 8/10/16.
 */
public class LinkedListRandomNode {
    /** @param head The linked list's head. Note that the head is guanranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int k = 1;
        ListNode p = head;
        ListNode randomNode = null;
        while (p != null) {
            int i = random.nextInt(k);
            if (i == 0) randomNode = p;
            p = p.next;
            ++k;
        }
        return randomNode.val;
    }

    private ListNode head;
    private Random random;
}
