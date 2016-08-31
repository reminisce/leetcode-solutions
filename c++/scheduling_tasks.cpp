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

using namespace std;

class Solution {
public:
    int findTotalTime(const string& tasks, int cooldown) {
        int total = 0;
        // key: the task that has been executed most recently
        // value: the task finish time
        unordered_map<char, size_t> char2IndexMap;
        for (size_t i = 0; i < tasks.size(); ++i) {
            auto it = char2IndexMap.find(tasks[i]);
            if (it == char2IndexMap.end()) {
                char2IndexMap[tasks[i]] = ++total;
            } else {
                int lastFinishTime = it->second;
                if (total - lastFinishTime >= cooldown) {
                    char2IndexMap[tasks[i]] = ++total;
                } else {
                    total = cooldown + lastFinishTime + 1;
                    char2IndexMap[tasks[i]] = total;
                }
            }
        }
        return total;
    }
};

int main() {
    Solution sol;
    string tasks = "12323";
    int cooldown = 3;
    cout << sol.findTotalTime(tasks, cooldown) << endl;
    return 0;
}