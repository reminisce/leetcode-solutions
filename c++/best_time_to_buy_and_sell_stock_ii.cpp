/* Say you have an array for which the ith element is the price of a given stock
 * on day i. Design an algorithm to find the maximum profit. You may complete as
 * many transactions as you like (ie, buy one and sell one share of the stock
 * multiple times). However, you may not engage in multiple transactions at the
 * same time (ie, you must sell the stock before you buy again).
 */

#include <cstdlib>
#include <vector>

using namespace std;

class Solution {
public:
    /* The basic idea is to find all the consecutive ascending sub-sequences
     * and buy in at the beginning of each sequence and sell out at the end of
     * it. Suppose there is a consecutive ascending sub-sequence, a[i], a[i+1],
     * ..., a[i+k]. The max profit gained from this sequence is a[i+k]-a[i] =
     * (a[i+k]-a[i+k-1])+(a[i+k-1]-a[i+k-2])+...+(a[i+1]-a[i]). Therefore, we
     * just need to check the difference between prices[i+1] and price[i]. If
     * the difference is positive, we should buy in on the i-th day and sell out
     * on the next day, then buy in again immediately to repeat this pattern.
     */
    int maxProfit(vector<int>& prices) {
        if (prices.size() < 2) {
            return 0;
        }
        
        int max_profit = 0;
        for (int i = 1; i < prices.size(); ++i) {
            int profit = prices[i] - prices[i-1];
            max_profit += (profit > 0? profit : 0);
        }
        
        return max_profit;
    }
};

int main(int argc, char** argv) {

    return 0;
}

