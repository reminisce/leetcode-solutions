/* Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary). You may assume that the intervals were
 * initially sorted according to their start times.
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],
 * [3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */

#include <cstdlib>
#include <vector>

using namespace std;


//  Definition for an interval.
struct Interval {
    int start;
    int end;

    Interval() : start(0), end(0) {}

    Interval(int s, int e) : start(s), end(e) {}
};

class Solution {
public:
    vector<Interval> insert(vector<Interval>& intervals, Interval newInterval) {
        int n = intervals.size();
        
        vector<Interval> res; // result
        
        // Find rightmost interval whose start is not greater than newInterval.start
        int left = 0, right = n-1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (newInterval.start < intervals[mid].start) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        int left_end = right;
        
        // Find leftmost interval whose end is not less than newInterval.end
        left = 0;
        right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (newInterval.end > intervals[mid].end) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        int right_end = left;
        
        // Merge newInterval and intervals[left_end], ..., intervals[right_end]
        // if necessary.
        if (left_end >= 0 && intervals[left_end].end >= newInterval.start) {
            newInterval.start = intervals[left_end--].start;
        }
        
        if (right_end < n && intervals[right_end].start <= newInterval.end) {
            newInterval.end = intervals[right_end++].end;
        }
        
        res.reserve(2+left_end+n-right_end);
        
        for (int i = 0; i <= left_end; ++i) {
            res.push_back(intervals[i]);
        }
        
        res.push_back(newInterval);
        
        for (int i = right_end; i < n; ++i) {
            res.push_back(intervals[i]);
        }
        
        return res;
    }
};

int main(int argc, char** argv) {
    vector<Interval> intervals;
    intervals.push_back(Interval(1, 5));
    intervals.push_back(Interval(6, 8));
    Interval newInterval(5, 6);
    Solution sol;
    intervals = sol.insert(intervals, newInterval);
    return 0;
}

