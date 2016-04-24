/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/

#include <string>
#include <unordered_map>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
    	int k = 10;
    	vector<string> rs;
    	if (s.size() < k) {
    		return rs;
    	}

    	unordered_map<string, int> substrMap;
    	// Get all the substrings of length 10 and
    	// put them in a hash map
    	for (int i = 0; i <= (int)s.size()-k; ++i) {
    		const string& str = s.substr(i, k);
    		auto it = substrMap.find(str);
    		if (it == substrMap.end()) {
    			substrMap[str] = 1;
    		} else {
    			if (it->second == 1) {
    				rs.push_back(str);
    			}
    			++(it->second);
    		}
    	}

    	return rs;
    }
};

int main()
{
	string s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
	Solution sol;
	vector<string> rs = sol.findRepeatedDnaSequences(s);
	for (const string& s : rs) {
		cout << s << ' ';
	}
	cout << endl;
	return 0;
}