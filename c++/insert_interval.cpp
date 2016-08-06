/*
Given a set of non-overlapping intervals,
insert a new interval into the intervals
(merge if necessary).

You may assume that the intervals were
initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge
[2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16],
insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9]
overlaps with [3,5],[6,7],[8,10].
*/

#include <vector>
#include <iostream>

using namespace std;

struct Interval {
    int start;
    int end;
    Interval() : start(0), end(0) {}
    Interval(int s, int e) : start(s), end(e) {}
};

class Solution {
public:
    vector<Interval> insert(vector<Interval>& intervals, Interval newInterval) {
        vector<Interval> res;
        size_t i = 0;
        while (i < intervals.size()) {
            if (intervals[i].start > newInterval.start) break;
            res.push_back(intervals[i++]);
        }

        if (res.empty() || res.back().end < newInterval.start) res.push_back(newInterval);
        else {
            res.back().end = max(newInterval.end, res.back().end);
        }

        while (i < intervals.size()) {
            if (res.back().end < intervals[i].start) res.push_back(intervals[i]);
            else {
                res.back().end = max(intervals[i].end, res.back().end);
            }
            ++i;
        }

        return res;
    }
};

int main()
{
    vector<Interval> intervals;
    intervals.push_back(Interval(1, 3));
    intervals.push_back(Interval(6, 9));
    Interval newInterval(2, 5);
    Solution sol;
    vector<Interval> res = sol.insert(intervals, newInterval);
    for (auto& interval : res) {
        cout << '[' << interval.start << ", " << interval.end << "] ";
    }
    cout << endl;
}
