#include <string>

class Solution {
public:
    int reverseDegree(std::string s) {
        int totalDegree = 0;
        
        for (int i = 0; i < s.length(); ++i) {
            // Position in the string (1-indexed)
            int stringPos = i + 1;
            
            // Position in the reversed alphabet ('a' = 26, 'b' = 25, ..., 'z' = 1)
            // 'z' - s[i] gives 0 for 'z', 1 for 'y', etc. Add 1 to make it 1-indexed.
            int revAlphabetPos = 26 - (s[i] - 'a');
            
            // Multiply and add to total sum
            totalDegree += stringPos * revAlphabetPos;
        }
        
        
        return totalDegree;
    }
};