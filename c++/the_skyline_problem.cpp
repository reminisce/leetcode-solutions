/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that
city when viewed from a distance. Now suppose you are given the locations and height of all
the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline
formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively,
and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15],
[5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2],
[x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal
line segment. Note that the last key point, where the rightmost building ends, is merely used to
mark the termination of the skyline, and always has zero height. Also, the ground in between any
two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0],
[15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline.
For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines
of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
*/

#include <set>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

class Solution {
public:
    /**
     * Save each left and right edge in a vector, left in terms of negative number,
     * right in terms of a positive number. Sort the vector based on x first.
     * Loop through the vector, if it's a left edge, insert it into the multiset;
     * if it's a right edge, erase it from the multiset. Each time of insertion/deletion,
     * check if the current height is the same as before, if not, it means the building
     * skyline needs to be updated.
     */
    vector<pair<int, int>> getSkyline(vector<vector<int>>& buildings) {
        vector<pair<int, int>> heights;
        for (auto& building : buildings) {
            heights.push_back({building[0], -building[2]});
            heights.push_back({building[1], building[2]});
        }
        sort(heights.begin(), heights.end());

        vector<pair<int, int>> res;
        multiset<int> heightSet;
        heightSet.insert(0);
        int preHeight = 0;
        for (auto& height : heights) {
            if (height.second < 0) heightSet.insert(-height.second);
            else heightSet.erase(heightSet.find(height.second));
            int curHeight = *heightSet.rbegin();
            if (preHeight != curHeight) {
                res.push_back({height.first, curHeight});
                preHeight = curHeight;
            }
        }

        return res;
    }
};

int main()
{
    vector<vector<int>> buildings;
    buildings.push_back({2, 9, 10});
    buildings.push_back({3, 7, 15});
    buildings.push_back({5, 12, 12});
    buildings.push_back({15, 20, 10});
    buildings.push_back({19, 24, 8});
    Solution sol;
    vector<pair<int, int>> res = sol.getSkyline(buildings);
    for (auto& it : res) {
        cout << it.first << ' ' << it.second << endl;
    }
    return 0;
}
