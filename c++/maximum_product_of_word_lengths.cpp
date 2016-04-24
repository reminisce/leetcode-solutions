/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j])
where the two words do not share common letters. You may assume that each word will contain
only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
*/

#include <vector>
#include <string>

using namespace std;

class Solution {
public:
	/**
	 * For each string, using an integer to represent
	 * the letters that the string has. Each bit of the
	 * integer represents whether the corresponding letter
	 * exists in the string.
	 */
    int maxProduct(vector<string>& words) {
    	if (words.empty()) {
    		return 0;
    	}
    	vector<int> strCode(words.size(), 0);

    	int index = 0;
    	for (const string& str : words) {
    		for (char c : str) {
    			strCode[index] |= (1 << (int)(c-'a'));
    		}
    		++index;
    	}

    	int rs = 0;
    	for (size_t i = 0; i < (int)words.size() - 1; ++i) {
    		for (size_t j = i + 1; j < words.size(); ++j) {
    			if ((strCode[i] & strCode[j]) == 0) {
    				rs = max((int)(words[i].size() * words[j].size()), rs);
    			}
    		}
    	}

    	return rs;
    }
};