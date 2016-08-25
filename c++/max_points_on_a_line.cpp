/*
Given n points on a 2D plane,
find the maximum number of points
that lie on the same straight line.
*/

#include <vector>
#include <unordered_map>
#include <iostream>

using namespace std;

struct Point {
    int x;
    int y;
    Point() : x(0), y(0) {}
    Point(int a, int b) : x(a), y(b) {}
};
 
class Solution {
public:
    int maxPoints(vector<Point>& points) {
        int maxNum = 0; 
        for (size_t i = 0; i < points.size(); ++i) {
            int duplicate = 1;
            unordered_map<double, int> slope2NumMap;
            for (size_t j = i+1; j < points.size(); ++j) {
                if (j == i) continue;

                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                        ++duplicate;
                        continue;
                }
                double k = (points[i].x == points[j].x? INT_MAX
                            : (double)(points[j].y - points[i].y) / (points[j].x - points[i].x));
                auto it = slope2NumMap.find(k);
                if (it == slope2NumMap.end()) {
                    slope2NumMap[k] = 1;
                } else {
                    ++it->second;
                }
            }

            int sum = 0;
            for (auto& entry : slope2NumMap) {
                sum = max(entry.second, sum);
            }
            maxNum = max(maxNum, sum+duplicate);
        }
        return maxNum;
    }
};

int main()
{
    vector<Point> points;
    points.push_back(Point(0, 0));
    points.push_back(Point(1, 1));
    points.push_back(Point(1, -1));
    Solution sol;
    cout << sol.maxPoints(points) << endl;
    return 0;
}
