#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxPalindromes(string s, int k) {
        const int n = s.length();
        // dp[i] stores the maximum number of valid palindrome substrings 
        // that can be formed within the first i characters of s.
        vector<int> dp(n + 1, 0);

        for (int i = k; i <= n; ++i) {
            // Option 1: Do not form a palindrome ending at i - 1
            dp[i] = dp[i - 1];

            // Option 2: Try forming a palindrome of length k ending at index i - 1
            if (isPalindrome(s, i - k, i - 1)) {
                dp[i] = max(dp[i], 1 + dp[i - k]);
            }

            // Option 3: Try forming a palindrome of length k + 1 ending at index i - 1
            if (isPalindrome(s, i - k - 1, i - 1)) {
                dp[i] = max(dp[i], 1 + dp[i - k - 1]);
            }
        }

        return dp[n];
    }

private:
    // Helper function to check if substring s[l...r] is a palindrome
    bool isPalindrome(const string& s, int l, int r) {
        if (l < 0) return false;
        while (l < r) {
            if (s[l++] != s[r--]) return false;
        }
        return true;
    }
};