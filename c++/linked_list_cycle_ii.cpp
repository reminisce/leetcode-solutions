/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
*/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

/**
 * Suppose slow runner marches one step at a time, and fast
 * runner marches two steps at a time. They must meet somewhere
 * in the loop if there is one. Suppose the non-loop part has
 * length X, the loop has length Y, and the meeting point in the
 * loop is K step away from where the loop begins. So, we have the
 * following two equations:
 * t = X + mY + K
 * 2t = X + nY + K
 * Get rid of t, we have
 * X + K = (n-2m)Y
 * This means that if we put two runners at the list begin and
 * the node k steps away from the loop begin, and let them
 * run one step at a time, they will definitely meet at where
 * the loop begins.
 */
class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        if (!head) return head;

        ListNode* fastRunner = head;
        ListNode* slowRunner = head;        
        while (fastRunner) {
            if (!fastRunner->next) {
                return nullptr;
            }
            fastRunner = fastRunner->next;
            if (!fastRunner->next) {
                return nullptr;
            }
            fastRunner = fastRunner->next;
            slowRunner = slowRunner->next;

            if (fastRunner == slowRunner) {
                break;
            }
        }

        if (!fastRunner) {
            return nullptr;
        }

        slowRunner = head;
        while (slowRunner != fastRunner) {
            slowRunner = slowRunner->next;
            fastRunner = fastRunner->next;
        }

        return slowRunner;
    }
};