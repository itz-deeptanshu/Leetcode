#include <string>
#include <vector>
#include <algorithm>

class Solution {
public:
    std::string lexGreaterPermutation(std::string s, std::string target) {
        int n = s.length();
        std::vector<int> count(26, 0);
        for (char c : s) {
            count[c - 'a']++;
        }

        // Determine the maximum length prefix of target we can form
        int max_match = 0;
        std::vector<int> temp_count = count;
        while (max_match < n && temp_count[target[max_match] - 'a'] > 0) {
            temp_count[target[max_match] - 'a']--;
            max_match++;
        }

        // Try divergence points from the deepest possible position down to 0
        for (int i = std::min(n - 1, max_match); i >= 0; --i) {
            // Count characters remaining if we match target[0 ... i - 1]
            std::vector<int> rem_count = count;
            for (int j = 0; j < i; ++j) {
                rem_count[target[j] - 'a']--;
            }

            // Find the smallest character strictly greater than target[i]
            int best_char = -1;
            for (int c = target[i] - 'a' + 1; c < 26; ++c) {
                if (rem_count[c] > 0) {
                    best_char = c;
                    break;
                }
            }

            // If a valid character exists, construct the result
            if (best_char != -1) {
                std::string result = "";
                // Matching prefix
                for (int j = 0; j < i; ++j) {
                    result += target[j];
                }
                // Divergence character
                result += (char)('a' + best_char);
                rem_count[best_char]--;

                // Append remaining characters in ascending order
                for (int c = 0; c < 26; ++c) {
                    while (rem_count[c] > 0) {
                        result += (char)('a' + c);
                        rem_count[c]--;
                    }
                }
                return result;
            }
        }

        return "";
    }
};