/* Given a binary search tree (BST), find the lowest common ancestor (LCA) of
 * two given nodes in the BST. According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes v and w as the
 * lowest node in T that has both v and w as descendants (where we allow a node
 * to be a descendant of itself).”
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
        int a = (p->val < q->val? p->val : q->val);
        int b = (p->val > q->val? p->val : q->val);
        
        return lowestCommonAncestorHelper(root, a, b);
    }
    
    // Assume p <= q without losing generality.
    // The node is the lowest common ancestor if either of the following is true:
    // (1) either p or q is the node->val;
    // (2) p and q are in the left and right branches of the node, respectively.
    // It must be noted that this solution assumes that p and q can be found
    // in the binary search tree. Otherwise, we must add an extra function
    // for confirming this assumption.
    TreeNode* lowestCommonAncestorHelper(TreeNode* node, int p, int q) {
        if (!node) {
            return nullptr;
        }
        
        if (p <= node->val && q >= node->val) {
            return node;
        }
        else if (p > node->val) {
            return lowestCommonAncestorHelper(node->right, p, q);
        }
        else if (q < node->val) {
            return lowestCommonAncestorHelper(node->left, p, q);
        }
        
        return nullptr;
    }
};

int main(int argc, char** argv) {

    return 0;
}

