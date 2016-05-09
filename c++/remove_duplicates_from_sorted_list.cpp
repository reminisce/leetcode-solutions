/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
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
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode* p = head;
        while (p) {
            ListNode* q = p->next;
            while (q && p->val == q->val) {
                q = q->next;
                p->next = q;
            }
            p = p->next;
        }

        return head;
    }
};

int main()
{
    ListNode* node1 = new ListNode(1);
    ListNode* node2 = new ListNode(2);
    ListNode* node3 = new ListNode(2);
    ListNode* node4 = new ListNode(2);
    ListNode* node5 = new ListNode(3);
    ListNode* node6 = new ListNode(3);
    ListNode* node7 = new ListNode(3);
    node1->next = node2;
    node2->next = node3;
    node3->next = node4;
    node4->next = node5;
    node5->next = node6;
    node6->next = node7;
    Solution sol;
    ListNode* head = sol.deleteDuplicates(node1);
    ListNode* p = head;
    while (p) {
        cout << p->val << ' ';
        p = p->next;
    }
    cout << endl;
    return 0;
}
