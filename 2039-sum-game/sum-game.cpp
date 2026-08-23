class Solution {
public:
    bool sumGame(string num) {
        int n = num.size();
        double balance = 0.0;
        
        for (int i = 0; i < n / 2; ++i) {
            if (num[i] == '?') {
                balance += 4.5;
            } else {
                balance += (num[i] - '0');
            }
        }
        
        for (int i = n / 2; i < n; ++i) {
            if (num[i] == '?') {
                balance -= 4.5;
            } else {
                balance -= (num[i] - '0');
            }
        }
        
        // If balance != 0, Alice wins; otherwise Bob wins.
        return balance != 0.0;
    }
};