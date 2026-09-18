class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        const int n = s.length();
        vector<string> ans;
        
        // leftmost[i] := the leftmost index of ('a' + i)
        vector<int> leftmost(26, n);
        // rightmost[i] := the rightmost index of ('a' + i)
        vector<int> rightmost(26, -1);
        
        for (int i = 0; i < n; ++i) {
            leftmost[s[i] - 'a'] = min(leftmost[s[i] - 'a'], i);
            rightmost[s[i] - 'a'] = max(rightmost[s[i] - 'a'], i);
        }
        
        auto getNewRight = [&](int i) {
            int right = rightmost[s[i] - 'a'];
            for (int j = i; j <= right; ++j) {
                // If a character appears before our starting index, 
                // this substring is invalid.
                if (leftmost[s[j] - 'a'] < i)
                    return -1;
                // Expand the right boundary dynamically if needed.
                right = max(right, rightmost[s[j] - 'a']);
            }
            return right;
        };
        
        int right = -1; // the rightmost index of the last chosen substring
        
        for (int i = 0; i < n; ++i) {
            // If the current index is the first appearance of the character
            if (i == leftmost[s[i] - 'a']) {
                const int newRight = getNewRight(i);
                if (newRight == -1)
                    continue;
                
                // If it overlaps with the previous one, we can replace it 
                // with the current one to get a smaller/better total length.
                if (i <= right && !ans.empty()) {
                    ans.back() = s.substr(i, newRight - i + 1);
                } else {
                    ans.push_back(s.substr(i, newRight - i + 1));
                }
                right = newRight;
            }
        }
        
        return ans;
    }
};