#include <iostream>
#include <vector>
#include <string>
#include <stack>

/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
*/

using namespace std;

class Solution {
public:
    /**
     * Read strings one by one and replace strings with pattern "num,#,#", which
     * means its a leaf node, with "#". If its a valid preorder string, there will
     * be only one "#" left.
     */
    bool isValidSerialization(const string& preorder) {
        vector<string> strs;
        splitPreorderString(preorder, strs);
        if (strs.size() == 1 && strs[0] == "#") {
            return true;
        }

        if (strs.size() == 2) {
            return false;
        }

        stack<string> curNodes; // stores the nodes processed so far
        curNodes.push(strs[0]);
        curNodes.push(strs[1]);
        for (size_t i = 2; i < strs.size(); ++i) {
            if (curNodes.empty()) {
                return false;
            }
            if (strs[i] == "#") {
                if (curNodes.top() == "#") {
                    curNodes.pop(); // pop out NULL
                    if (curNodes.empty() || curNodes.top() == "#") {
                        return false;
                    } else {
                        curNodes.pop(); // pop out number
                        // keep removing leaf nodes
                        while (!curNodes.empty() && curNodes.top() == "#") {
                            curNodes.pop();
                            if (curNodes.empty()) {
                                return false;
                            }
                            if (curNodes.top() == "#") {
                                return false;
                            }
                            curNodes.pop();
                        } // cannot remove more leaf nodes
                        curNodes.push("#"); // replace original leaf node with NULL
                    }
                } else {
                    curNodes.push(strs[i]);
                }
            } else {
                curNodes.push(strs[i]);
            }
        }

        return (curNodes.size() == 1 && curNodes.top() == "#");
    }

    /**
     * See NULL also as a node. It has one in-degree. The root has only
     * two out degree. The other nodes have only one in degree and two
     * out-degrees of each own. The numbers of in-degrees and out-degrees
     * should be equal. Loop through the nodes, if it's a internal nodes,
     * neither a root nor a NULL, add 2 to difference between out and in degrees.
     * If it's a NULL, subtract 1 from the difference.
     */
    bool isValidSerialization2(const string& preorder) {
        vector<string> strs;
        splitPreorderString(preorder, strs);
        int diff = 1; // compensate the root
        for (size_t i = 0; i < strs.size(); ++i) {
            if (--diff < 0) {
                return false;
            }
            if (strs[i] != "#") {
                diff += 2;
            }
        }
        return diff == 0;
    }

    /**
     * Split preorder str by ','
     */
    void splitPreorderString(const string& str, vector<string>& strs) {
        int i = 0;
        int j = i;
        while (j < str.size()) {
            while (j < str.size() && str[j] != ',') {
                ++j;
            }
            strs.push_back(str.substr(i, j-i));
            i = ++j;
        }
#if 0
        cout << "splitted strings:\n";
        for (const string& str : strs) {
            cout << str << ", ";
        }
        cout << "\n";
#endif
    }
};

int main()
{
    string preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
    //string preorder = "1,#,#,#,#";
    Solution sol;
    bool ret = sol.isValidSerialization2(preorder);
    cout << preorder << (ret? " is" : " is not") << " a preorder serialization of a bst" << endl;
    return 0;
}

