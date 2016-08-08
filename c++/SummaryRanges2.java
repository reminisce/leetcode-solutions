/**
 * Created on 8/8/16.
 * Given a data stream input of non-negative integers
 * a1, a2, ..., an, ..., summarize the numbers seen so
 * far as a list of disjoint intervals.
 *
 * For example, suppose the integers from the data
 * stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 *
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * Follow up:
 * What if there are lots of merges and the number of
 * disjoint intervals are small compared to the data stream's size?
 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class SummaryRanges2 {

    public static void main(String[] args) {
        SummaryRanges2 app = new SummaryRanges2();
        int[] nums = {1, 3, 2, 6, 7};
        for (int num : nums) {
            app.addNum2(num);
            System.out.println(app.getIntervals2().toString());
        }
    }

    /** Initialize your data structure here. */
    public SummaryRanges2() {
        intervals = new ArrayList<>();
        tree = new TreeMap<>();
    }

    public void addNum(int val) {
        Interval newInterval = new Interval(val, val);
        if (intervals.isEmpty()) {
            intervals.add(newInterval);
            return;
        }

        List<Interval> intervals2 = new ArrayList<>(intervals.size());
        int i = 0;
        while (i < intervals.size() && intervals.get(i).start < val) {
            intervals2.add(intervals.get(i));
            ++i;
        }

        if (intervals2.isEmpty()) {
            intervals2.add(newInterval);
        } else {
            Interval lastInterval = intervals2.get(intervals2.size()-1);
            if (lastInterval.end + 1 < val) {
                intervals2.add(newInterval);
            } else {
                lastInterval.end = Math.max(lastInterval.end, val);
            }
        }

        while (i < intervals.size()) {
            Interval lastInterval = intervals2.get(intervals2.size()-1);
            if (lastInterval.end + 1 < intervals.get(i).start) {
                intervals2.add(intervals.get(i));
            } else {
                lastInterval.end = Math.max(lastInterval.end, intervals.get(i).end);
            }
            ++i;
        }

        intervals = intervals2;
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    private List<Interval> intervals;

    /*********************** Another impl using TreeMap *********************/
    // https://discuss.leetcode.com/topic/46887/java-solution-using-treemap-real-o-logn-per-adding
    private TreeMap<Integer, Interval> tree;

    public void addNum2(int val) {
        if(tree.containsKey(val)) return;
        Integer l = tree.lowerKey(val);
        Integer h = tree.higherKey(val);
        if(l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) {
            tree.get(l).end = tree.get(h).end;
            tree.remove(h);
        } else if(l != null && tree.get(l).end + 1 >= val) {
            tree.get(l).end = Math.max(tree.get(l).end, val);
        } else if(h != null && h == val + 1) {
            tree.put(val, new Interval(val, tree.get(h).end));
            tree.remove(h);
        } else {
            tree.put(val, new Interval(val, val));
        }
    }

    public List<Interval> getIntervals2() {
        return new ArrayList<>(tree.values());
    }
}

/**
 * Your SummaryRanges2 object will be instantiated and called as such:
 * SummaryRanges2 obj = new SummaryRanges2();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
