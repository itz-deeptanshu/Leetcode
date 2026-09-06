class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // If target string is longer than source string, impossible to form
        if (m < n) {
            return 0;
        }

        // dp[j] stores the number of distinct subsequences of s that equal t[0...j-1]
        int[] dp = new int[n + 1];

        // An empty target t can always be formed by deleting all characters of s
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            char sChar = s.charAt(i);
            // Traverse backwards to use the values from the previous iteration of s
            for (int j = n; j >= 1; j--) {
                if (sChar == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}