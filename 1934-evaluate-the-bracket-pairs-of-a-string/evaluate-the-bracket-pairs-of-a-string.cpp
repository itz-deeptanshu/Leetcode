#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
public:
    string evaluate(string s, vector<vector<string>>& knowledge) {
        // Step 1: Store knowledge pairs in a hash map for fast lookup
        unordered_map<string, string> mp;
        for (const auto& pair : knowledge) {
            mp[pair[0]] = pair[1];
        }

        string result = "";
        int n = s.length();
        
        // Step 2: Iterate through the string to find and evaluate brackets
        for (int i = 0; i < n; ) {
            if (s[i] == '(') {
                int j = i + 1;
                // Find the closing bracket
                while (j < n && s[j] != ')') {
                    j++;
                }
                
                // Extract the key inside the brackets
                string key = s.substr(i + 1, j - i - 1);
                
                // Append value or '?' based on knowledge
                if (mp.find(key) != mp.end()) {
                    result += mp[key];
                } else {
                    result += "?";
                }
                
                // Move index past the closing bracket
                i = j + 1;
            } else {
                result += s[i];
                i++;
            }
        }
        
        return result;
    }
};