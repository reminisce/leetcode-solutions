/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

struct Interval {
    int start;
    int end;
    Interval() : start(0), end(0) {}
    Interval(int s, int e) : start(s), end(e) {}
};

struct IntervalComparator {
    bool operator()(const Interval& interval1, const Interval& interval2) const {
        return interval1.start < interval2.start;
    }
};

class Solution {
public:
    vector<Interval> merge(vector<Interval>& intervals) {
        vector<Interval> res;
        if (intervals.empty()) return res;
        sort(intervals.begin(), intervals.end(), IntervalComparator());
        res.push_back(intervals[0]);
        for (size_t i = 1; i < intervals.size(); ++i) {
            if (intervals[i].start > res.back().end) res.push_back(intervals[i]);
            else {
                res.back().end = max(res.back().end, intervals[i].end);
            }
        }
        return res;
    }
};
