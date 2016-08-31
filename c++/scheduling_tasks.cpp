/**
* (F)
* Created on 8/29/16.
* Given a series of tasks and cooldown time,
* find the smallest total time needed to
* execute all the tasks one by one.
* For example, given 12323 and k = 3,
* the total execution sequence should be
* 123__23, that is 7.
*/

#include <iostream>
#include <string>
#include <unordered_map>
#include <queue>
#include <vector>

using namespace std;

class Solution {
public:
    // the tasks' order cannot be changed
    int findTotalTime(const string& tasks, int cooldown) {
        int total = 0;
        string res = "";
        // key: the task that has been executed most recently
        // value: the task finish time
        unordered_map<char, size_t> char2IndexMap;
        for (size_t i = 0; i < tasks.size(); ++i) {
            auto it = char2IndexMap.find(tasks[i]);
            if (it == char2IndexMap.end()) {
                char2IndexMap[tasks[i]] = ++total;
                res.push_back(tasks[i]);
            } else {
                int lastFinishTime = it->second;
                if (total - lastFinishTime >= cooldown) {
                    char2IndexMap[tasks[i]] = ++total;
                    res.push_back(tasks[i]);
                } else {
                    for (int j = 0; j < cooldown - total + lastFinishTime; ++j) {
                        res.push_back('#');
                    }
                    total = cooldown + lastFinishTime + 1;
                    char2IndexMap[tasks[i]] = total;
                    res.push_back(tasks[i]);
                }
            }
        }
        cout << "findTotalTime: " << res << endl;
        return total;
    }

    // the tasks' order can be changed
    int findTotalTime2(const string& tasks, int cooldown) {
        unordered_map<char, int> freq;
        for (char task : tasks) {
            auto it = freq.find(task);
            if (it == freq.end()) {
                freq[task] = 1;
            } else {
                ++it->second;
            }
        }

        priority_queue<pair<int, char>, vector<pair<int, char>>> pq;
        for (auto& entry : freq) {
            pq.push({entry.second, entry.first});
        }

        int totalTime = 0;
        int remainLength = tasks.size();
        string res = "";
        while (!pq.empty()) {
            int cnt = min(remainLength, cooldown+1);
            vector<pair<int, char>> candidates;
            for (int i = 0; i < cnt; ++i) {
                if (pq.empty()) {
                    ++totalTime;
                    res.push_back('#');
                    continue;
                }
                auto p = pq.top();
                pq.pop();
                if (--p.first > 0) {
                    candidates.push_back(p);
                }
                res.push_back(p.second);
                ++totalTime;
                --remainLength;
            }
            for (auto& p : candidates) {
                pq.push(p);
            }
        }
        cout << "findTotalTime2: " << res << endl;
        return totalTime;
    }
};

int main() {
    Solution sol;
    string tasks = "12323";
    int cooldown = 3;
    cout << sol.findTotalTime(tasks, cooldown) << endl;
    cout << sol.findTotalTime2(tasks, cooldown) << endl;
    return 0;
}