import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 7/20/16.
 * Given a binary tree, find all the
 * duplicate subtrees.
 */

public class FindDuplicateSubtrees {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(4);

        List<String> inorderSubtrees = new ArrayList<>();
        FindDuplicateSubtrees app = new FindDuplicateSubtrees();
        // app.getInorderSubtrees(root, inorderSubtrees);
        // app.getInorderSerializedSubtrees(root, inorderSubtrees);
        app.findDuplicateSubtrees(root);
    }

    public List<List<TreeNode>> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> str2NodeListMap = new HashMap<>();
        findDuplicateSubtreesHelper(root, str2NodeListMap);
        printStr2NodeListMap(str2NodeListMap);
        List<List<TreeNode>> res = new ArrayList<>();
        for (List<TreeNode> list : str2NodeListMap.values()) {
            res.add(list);
        }
        return res;
    }

    private String findDuplicateSubtreesHelper(TreeNode node, Map<String, List<TreeNode>> str2NodeListMap) {
        if (node == null) return "# ";
        String postOrderKey = findDuplicateSubtreesHelper(node.left, str2NodeListMap);
        postOrderKey += findDuplicateSubtreesHelper(node.right, str2NodeListMap);
        postOrderKey += node.val + " ";
        if (!str2NodeListMap.containsKey(postOrderKey)) {
            str2NodeListMap.put(postOrderKey, new ArrayList<>());
        }
        str2NodeListMap.get(postOrderKey).add(node);
        return postOrderKey;
    }

    private void printStr2NodeListMap(Map<String, List<TreeNode>> str2NodeListMap) {
        for (Map.Entry<String, List<TreeNode>> entry : str2NodeListMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }
    }

    private String getInorderSubtrees(TreeNode node, List<String> inorderSubtrees) {
        if (null == node) return "";
        String temp = getInorderSubtrees(node.left, inorderSubtrees);
        temp += node.val;
        temp += getInorderSubtrees(node.right, inorderSubtrees);
        inorderSubtrees.add(temp);
        return temp;
    }

    private String getInorderSerializedSubtrees(TreeNode node, List<String> inorderSerializedSubtrees) {
        if (null == node) return "# ";
        String temp = getInorderSerializedSubtrees(node.left, inorderSerializedSubtrees);
        temp += node.val + " ";
        temp += getInorderSerializedSubtrees(node.right, inorderSerializedSubtrees);
        inorderSerializedSubtrees.add(temp);
        return temp;
    }
}
