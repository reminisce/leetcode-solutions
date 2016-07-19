/**
 * Created on 7/19/16.
 */
public class QuadTreeUtil {

    public static QuadTreeNode and(QuadTreeNode node1, QuadTreeNode node2) {
        QuadTreeNode node = new QuadTreeNode();
        if (node1.isFull && node2.isFull) {
            node.isFull = true;
            node.color = node1.color & node2.color;
        } else if (node1.isFull) {
            if (node1.color == 0) {
                node.isFull = true;
                node.color = 0;
            } else {
                node.isFull = false;
                for (int i = 0; i < 4; ++i) {
                    node.children[i] = and(node1, node2.children[i]);
                }
            }
        } else if (node2.isFull) {
            return and(node2, node1);
        } else {
            node.isFull = false;
            for (int i = 0; i < 4; ++i) {
                node.children[i] = and(node1.children[i], node2.children[i]);
            }
        }
        return node;
    }

    public static class QuadTreeNode {
        public QuadTreeNode() {
            children = new QuadTreeNode[4];
            isFull = false;
            color = 0;
        }
        public QuadTreeNode[] children;
        // if the node is not full, it must have none null children
        public boolean isFull; // The node is full if it has only one color
        public int color; // 0 / 1
    }
}
