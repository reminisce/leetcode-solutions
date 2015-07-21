/* Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
 * in the tree. According to the definition of LCA on Wikipedia: “The lowest
 * common ancestor is defined between two nodes v and w as the lowest node in T
 * that has both v and w as descendants (where we allow a node to be a
 * descendant of itself).”
 */

#include <cstdlib>

using namespace std;


// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    TreeNode(int x) : val(x), left(NULL), right(NULL) {
    }
};


class Solution {
public:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (!root) {
            return nullptr;
        }
    }
    
    TreeNode* lowestCommonAncestorHelper(TreeNode* node, TreeNode* p, TreeNode* q) {
        if (!node) {
            return nullptr;
        }
        
        if (node == p || node == q) {
            return node;
        }
        
        TreeNode* left = lowestCommonAncestorHelper(node->left, p, q);
        TreeNode* right = lowestCommonAncestorHelper(node->right, p, q);
        if (!left && !right) {
            return node;
        }
        
        return left? left : right;
    }
};

int main(int argc, char** argv) {

    return 0;
}

