/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */

 #include <stack>
 #include <iostream>
 #include <vector>

 using namespace std;

/**
 * Definition for binary tree
 */
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class BSTIterator {
public:
	// Travel down to the left-most branch and save the
	// nodes in a stack from root to left-most leaf node
    BSTIterator(TreeNode *root) {
    	if (root) {
    		TreeNode* nextNode = root;
    		m_nodeStack.push(root);
    		while (nextNode->left) {
    			nextNode = nextNode->left;
    			m_nodeStack.push(nextNode);
    		}
    	}
    }

    /** @return whether we have a next smallest number */
    bool hasNext() {
    	return !m_nodeStack.empty() && m_nodeStack.top() != nullptr;
    }

    /** @return the next smallest number */
    int next() {
    	if (!hasNext()) {
    		return -1;
    	}
    	// The top node on the stack is the next node.
    	TreeNode* nextNode = m_nodeStack.top();
    	m_nodeStack.pop();
    	int nextVal = nextNode->val;
    	// If the next node has right subtree,
    	// Need to push all the nodes of the
    	// subtree's left-most branch into the stack.
    	if (nextNode->right) {
    		nextNode = nextNode->right;
    		m_nodeStack.push(nextNode);
    		while (nextNode->left) {
    			nextNode = nextNode->left;
    			m_nodeStack.push(nextNode);
    		}
    	}
    	return nextVal;
    }

private:
	stack<TreeNode*> m_nodeStack;
};

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = BSTIterator(root);
 * while (i.hasNext()) cout << i.next();
 */

int main()
{
	vector<TreeNode*> nodes;
	for (int i = 0; i < 7; ++i) {
		nodes.push_back(new TreeNode(i));
	}
	nodes[1]->left = nodes[0];
	nodes[1]->right = nodes[3];
	nodes[3]->left = nodes[2];
	nodes[4]->left = nodes[1];
	nodes[4]->right = nodes[5];
	nodes[5]->right = nodes[6];
	BSTIterator it = BSTIterator(nodes[4]);
	while (it.hasNext()) {
		cout << it.next() << ' ';
	}
	cout << endl;
	return 0;
}
