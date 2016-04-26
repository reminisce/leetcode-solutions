/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
 
class Solution {
public:
    bool hasCycle(ListNode *head) {
        if (!head) {
            return false;
        }
        
        ListNode* fastRunner = head;
        ListNode* runner = head;
        while (fastRunner != nullptr) {
            fastRunner = fastRunner->next;
            if (!fastRunner) {
                return false;
            }
            fastRunner = fastRunner->next;
            runner = runner->next;
            if (fastRunner == runner) {
                break;
            }
        }

        if (!fastRunner) {
            return false;
        }

        return true;
    }
};