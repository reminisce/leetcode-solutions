/**
 * Created on 5/22/16.
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode lastNode = null; // the node before slowRunner
        ListNode slowRunner = head;
        ListNode fastRunner = head;
        while (fastRunner != null && fastRunner.next != null && fastRunner.next.next != null) {
            lastNode = slowRunner;
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        }
        TreeNode root = new TreeNode(slowRunner.val);
        if (lastNode != null) {
            lastNode.next = null;
            root.left = sortedListToBST(head);
        }
        root.right = sortedListToBST(slowRunner.next);
        return root;
    }
}
