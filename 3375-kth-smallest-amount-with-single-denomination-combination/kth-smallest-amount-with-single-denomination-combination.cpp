#include <vector>
#include <numeric>
#include <algorithm>

class Solution {
public:
    // Helper function to compute Greatest Common Divisor
    long long gcd(long long a, long long b) {
        return std::gcd(a, b);
    }

    // Helper function to compute Least Common Multiple with overflow guard
    long long lcm(long long a, long long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }

    // Count numbers <= target that are multiples of at least one coin
    long long countMultiples(long long target, const std::vector<int>& coins) {
        int n = coins.size();
        long long total = 0;

        // Iterate through all 2^n - 1 non-empty subsets
        for (int mask = 1; mask < (1 << n); ++mask) {
            long long current_lcm = 1;
            int bit_count = 0;
            bool overflow = false;

            for (int i = 0; i < n; ++i) {
                if (mask & (1 << i)) {
                    bit_count++;
                    current_lcm = lcm(current_lcm, coins[i]);
                    if (current_lcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if (bit_count % 2 == 1) {
                total += target / current_lcm;
            } else {
                total -= target / current_lcm;
            }
        }

        return total;
    }

    long long findKthSmallest(std::vector<int>& coins, int k) {
        long long min_coin = *std::min_element(coins.begin(), coins.end());
        long long low = 1;
        long long high = min_coin * k;
        long long ans = high;

        while (low <= high) {
            long long mid = low + (high - low) / 2;
            if (countMultiples(mid, coins) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
};