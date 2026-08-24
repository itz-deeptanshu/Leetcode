class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Compute prefix sums in-place
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Base case: at index n - 1, the only choice yields stones[n - 1]
        int maxDiff = stones[n - 1];
        
        // Work backwards from n - 2 down to 1
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, stones[i] - maxDiff);
        }
        
        return maxDiff;
    }
}