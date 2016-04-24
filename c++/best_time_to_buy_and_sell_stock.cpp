#include <iostream>
#include <cstdlib>
#include <vector>

using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if (prices.empty()) {
            return 0;
        }

        int cur_min_price = prices[0];
        int max_profit = 0;
        // Keep tracking the max profit of selling the stock
        // on the i-th day by updating the the min price
        // from the 0-th to (i-1)-th day.
        for (int i = 1; i < prices.size(); ++i) {
            int cur_profit = prices[i] - cur_min_price;
            if (cur_profit > max_profit) {
                max_profit = cur_profit;
            }
            if (cur_min_price > prices[i]) {
                cur_min_price = prices[i];
            }
        }
        
        return max_profit;
    }
};

int main(int argc, char** argv) {

    return 0;
}

