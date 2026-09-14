#include <vector>
#include <map>
#include <algorithm>

using namespace std;

class Solution {
public:
    int largestOverlap(vector<vector<int>>& img1, vector<vector<int>>& img2) {
        int n = img1.size();
        vector<pair<int, int>> list1, list2;
        
        // Collect coordinates of all 1s
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                if (img1[r][c] == 1) list1.push_back({r, c});
                if (img2[r][c] == 1) list2.push_back({r, c});
            }
        }
        
        // Map to store the frequency of each shift vector
        map<pair<int, int>, int> shiftCount;
        int maxOverlap = 0;
        
        for (auto& p1 : list1) {
            for (auto& p2 : list2) {
                int rowShift = p2.first - p1.first;
                int colShift = p2.second - p1.second;
                
                shiftCount[{rowShift, colShift}]++;
                maxOverlap = max(maxOverlap, shiftCount[{rowShift, colShift}]);
            }
        }
        
        return maxOverlap;
    }
};