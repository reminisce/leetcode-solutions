/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
*/

#include <iostream>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* reverseBetween(ListNode* head, int m, int n) {
        if (!head) return head;
        ListNode* nodeBeforeM = nullptr;
        if (m > 1) {
            nodeBeforeM = head;
            for (int i = 1; i < m-1; ++i) {
                nodeBeforeM = nodeBeforeM->next;
            }
        }
        // subTail is the m-th node
        ListNode* subTail = (nodeBeforeM == nullptr? head : nodeBeforeM->next);
        ListNode* p = subTail;
        ListNode* q = p->next;

        // Reverse the linked list segment between m-th and n-th node
        for (int i = 1; i <= n-m; ++i) {
            ListNode* s = q->next;
            q->next = p;
            p = q;
            q = s;
        }
        // q is the (n+1)-th node
        subTail->next = q;

        // p is the n-th node, and now
        // becomes the m-th node in the new linked list
        if (nodeBeforeM) {
            nodeBeforeM->next = p;
            return head;
        } else {
            return p;
        }
    }
};

int main()
{
    ListNode* head = new ListNode(1);
    head->next = new ListNode(2);
    head->next->next = new ListNode(3);
    head->next->next->next = new ListNode(4);
    head->next->next->next->next = new ListNode(5);
    int m = 3, n = 3;
    Solution sol;
    head = sol.reverseBetween(head, m, n);
    ListNode* p = head;
    while (p) {
        cout << p->val << ' ';
        p = p->next;
    }
    cout << endl;
    return 0;
}
