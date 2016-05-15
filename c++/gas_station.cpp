/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i
to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

class Solution {
public:
    /**
     * 1. If gas[i] < cost[i], then i cannot be the start point
     * 2. When sum(gas[i]-cost[i]) < 0 for all i = 0,...,n-1, there is no way
     *    to complete the circuit; otherwise, there must be a solution to do it.
     * How to determine the start point? There are three propositions to consider:
     * 1. If i is a start point, then starting from i can get to any other gas stations.
     * 2. If i is the unique answer, then there is no j such that j != i, and starting from
     *    j can get to i; otherwise, starting from j can complete the circuit which violates
     *    the assumption that the solution is unique.
     * 3. If i is the solution, starting from any one of 0,...,i-1 cannot get to i. But starting
     *    from i can get to i+1,...,n-1.
     * So here is the solution:
     * 1. If sum(gas[i]-cost[i]) < 0 for all i, return -1.
     * 2. Starting from i = 0 and accumulate gas[i]-cost[i] until sum < 0 at i1. Then, i = 0
     *    is not a starting point for sure. In addition, 1,...,i1-1 cannot be starting point
     *    either; otherwise, i = 0 can be reached from any one of 1,...,i1-1 to make it a solution.
     * 3. Then, reset sum = 0, and start to accumulate again until starting from somewhere reaches n-1.
     */
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int curSum = 0;
        int netSum = 0; // check whether can complete the circuit
        int start = 0;
        for (int i = 0; i < (int)gas.size(); ++i) {
            netSum += (gas[i] - cost[i]);
            curSum += (gas[i] - cost[i]);
            if (curSum < 0) {
                start = i + 1;
                curSum = 0;
            }
        }

        if (netSum < 0) return -1;
        return start;
    }
};