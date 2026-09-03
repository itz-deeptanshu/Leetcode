#include <vector>
#include <algorithm>

class Solution {
public:
    bool uniformArray(std::vector<int>& nums1) {
        int min_val = nums1[0];
        bool has_odd = false;

        for (int x : nums1) {
            if (x < min_val) {
                min_val = x;
            }
            if (x % 2 != 0) {
                has_odd = true;
            }
        }

        // If the minimum element is odd, we can always make all elements odd.
        if (min_val % 2 != 0) {
            return true;
        }

        // If the minimum element is even, we can only succeed if there are no odd numbers.
        return !has_odd;
    }
};