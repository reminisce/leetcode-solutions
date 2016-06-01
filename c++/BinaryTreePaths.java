import java.util.ArrayList;
import java.util.List;

/**
 * Created on 5/31/16.
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 *
 *   1
 * /  \
 * 2   3
 *  \
 *   5
 * All root-to-leaf paths are:
 *
 * ["1->2->5", "1->3"]
 */

public class BinaryTreePaths {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);

        BinaryTreePaths app = new BinaryTreePaths();
        List<String> paths = app.binaryTreePaths(root);
        for (String path : paths) {
            System.out.println(path);
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        getPaths(root, "", paths);
        return paths;
    }

    private void getPaths(TreeNode root, String path, List<String> paths) {
        if (null == root) return;

        if (null == root.left && null == root.right) {
            paths.add(path + root.val);
            return;
        }

        getPaths(root.left, path + root.val + "->", paths);
        getPaths(root.right, path + root.val + "->", paths);
    }
}
