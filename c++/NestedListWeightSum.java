import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created on 8/8/16.
 * Given a nested list of integers, return
 * the sum of all integers in the list
 * weighted by their depth.
 *
 * Each element is either an integer, or
 * a list -- whose elements may also be
 * integers or other lists.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 10.
 * (four 1's at depth 2, one 2 at depth 1)
 *
 * Example 2:
 * Given the list [1,[4,[6]]], return 27.
 * (one 1 at depth 1, one 4 at depth 2, and
 * one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 */

public class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            sum += depthSumHelper(ni, 1);
        }
        return sum;
    }

    private int depthSumHelper(NestedInteger nestedInteger, int level) {
        if (nestedInteger.isInteger()) return nestedInteger.getInteger() * level;
        List<NestedInteger> nestedList = nestedInteger.getList();
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            sum += depthSumHelper(ni, level+1);
        }
        return sum;
    }

    private int depthSumIterative(List<NestedInteger> nestedList) {
        int level = 1;
        int sum = 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; ++i) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) sum += ni.getInteger() * level;
                else {
                    List<NestedInteger> niList = ni.getList();
                    for (NestedInteger nestedInteger : niList) {
                        queue.offer(nestedInteger);
                    }
                }
            }
            ++level;
        }
        return sum;
    }
}
