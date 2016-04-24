#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int numTrees(int n) {
    	vector<int> v(n+1, 0);
    	v[0] = 1;
    	for (int k = 1; k <= n; ++k) {
    		for (int i = 1; i <= k; ++i) {
    			v[k] += v[i-1] * v[k-i];
    		}
    	}

    	return v[n];
    }
};

int main(int argc, char** argv)
{
	Solution solution;
	int n = 0;
	cin >> n;
	int result = solution.numTrees(n);
	cout << result << endl;
	return result;
}