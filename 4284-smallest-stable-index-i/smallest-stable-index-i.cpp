#include <vector>
#include <algorithm>

class Solution {
public:
    int firstStableIndex(std::vector<int>& nums, int k) {
        int n = nums.size();
        if (n == 0) return -1;

        // Precompute suffix minimums
        std::vector<int> suff_min(n);
        suff_min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suff_min[i] = std::min(nums[i], suff_min[i + 1]);
        }

        // Traverse from left to right tracking running max
        int pref_max = nums[0];
        for (int i = 0; i < n; ++i) {
            pref_max = std::max(pref_max, nums[i]);
            if (pref_max - suff_min[i] <= k) {
                return i;
            }
        }

        return -1;
    }
};