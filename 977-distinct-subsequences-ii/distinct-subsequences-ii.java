class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        // endsCount[i] stores the number of distinct subsequences ending with ('a' + i)
        long[] endsCount = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            
            // Subsequences ending with ch: all existing subsequences + ch itself
            long newEnds = (total + 1) % MOD;
            
            // Update total: add newEnds, remove the old endsCount[idx]
            total = (total + newEnds - endsCount[idx] + MOD) % MOD;
            
            // Update the count for the current character
            endsCount[idx] = newEnds;
        }

        return (int) total;
    }
}