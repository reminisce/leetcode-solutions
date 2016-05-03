/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

#include <vector>
#include <queue>
#include <iostream>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

struct GreaterListNode {
    bool operator() (const ListNode* node1, const ListNode* node2) const {
        return node1->val > node2->val;
    }
};

class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if (lists.empty()) return nullptr;

        priority_queue<ListNode*, vector<ListNode*>, GreaterListNode> maxHeap;
        for (size_t i = 0; i < lists.size(); ++i) {
            if (lists[i]) {
                maxHeap.push(lists[i]);
            }
        }

        if (maxHeap.empty()) return nullptr;

        ListNode* head = maxHeap.top();
        maxHeap.pop();
        if (head->next) {
            maxHeap.push(head->next);
        }
        ListNode* tail = head;
        tail->next = nullptr;

        while (!maxHeap.empty()) {
            tail->next = maxHeap.top();
            maxHeap.pop();
            tail = tail->next;
            if (tail->next) {
                maxHeap.push(tail->next);
            }
            tail->next = nullptr;
        }

        return head;
    }
};

int main()
{
    vector<ListNode*> lists;
    ListNode* head = new ListNode(1);
    ListNode* tail = new ListNode(4);
    head->next = tail;
    lists.push_back(head);

    head = new ListNode(2);
    // tail = new ListNode(5);
    // head->next = tail;
    head->next = nullptr;
    lists.push_back(head);

    head = new ListNode(3);
    tail = new ListNode(6);
    head->next = tail;
    lists.push_back(head);

    Solution sol;
    ListNode* newHead = sol.mergeKLists(lists);

    for (ListNode* node = newHead; node != nullptr; node = node->next) {
        cout << node->val << ' ';
    }
    cout << endl;
    return 0;
}
