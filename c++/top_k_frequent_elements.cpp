/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

#include <unordered_map>
#include <queue>
#include <iostream>

using namespace std;

struct GreaterPair {
    bool operator()(const pair<int, int>& p1, const pair<int, int>& p2) const {
        return p1.second > p2.second;
    }
};

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {       
        vector<int> rs(k);
        if (k <= 0) return rs;

        unordered_map<int, int> frequencyMap;
        for (int num : nums) {
            auto it = frequencyMap.find(num);
            if (it == frequencyMap.end()) {
                frequencyMap[num] = 1;
            } else {
                ++frequencyMap[num];
            }
        }

        priority_queue<pair<int, int>, vector<pair<int, int>>, GreaterPair> q;
        for (auto& it : frequencyMap) {
            if (q.size() < k) {
                q.push(it);
            } else {
                if (it.second > q.top().second) {
                    q.pop();
                    q.push(it);
                }
            }
        }

        int index = k;
        while (!q.empty()) {
            rs[--index] = q.top().first;
            q.pop();
        }
        return rs;
    }
};

int main()
{
    vector<int> nums = {1,1,1,2,2,3};
    int k = 2;
    Solution sol;
    vector<int> rs = sol.topKFrequent(nums, k);
    for (int num : rs) {
        cout << num << ' ';
    }
    cout << endl;
    return 0;
}
