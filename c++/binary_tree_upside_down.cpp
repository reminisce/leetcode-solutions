/**
* Created on 6/12/16.
* Given a binary tree where all the right nodes are either leaf nodes with a
* sibling (a left node that shares the same parent node) or empty, flip it
* upside down and turn it into a tree where the original right nodes turned
* into left leaf nodes. Return the new root.
*
* For example:
*
* Given a binary tree {1,2,3,4,5},
*
*     1
*    / \
*   2  3
*  / \
* 4   5
*
* return the root of the binary tree [4,5,2,#,#,3,1].
*
*
*     1
*    /
*   2--3
*  /
* 4--5
*
*     4
*    / \
*   5  2
*     / \
*    3  1
*/

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    TreeNode* upsideDownBinaryTreeRecursive(TreeNode* root) {
        return upsideDownBinaryTreeRecursive(root, nullptr);
    }   

    TreeNode* upsideDownBinaryTreeRecursive(TreeNode* node, TreeNode* parent) {
        if (!node) return parent;
        TreeNode* newRoot = upsideDownBinaryTreeRecursive(node->left, node);
        node->left = (parent == nullptr? nullptr : parent->right);
        node->right = parent;
        return newRoot;
    }

    TreeNode* upsideDownBinaryTreeIterative(TreeNode* root) {
        if (!root) return nullptr;
        TreeNode* curNode = root, *preNode = nullptr, *tmpRightNode = nullptr;
        while (curNode) {
            TreeNode* nextNode = curNode->left;
            curNode->left = tmpRightNode;
            tmpRightNode = curNode->right;
            curNode->right = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }   
};

int main()
{
    Solution sol;
}
