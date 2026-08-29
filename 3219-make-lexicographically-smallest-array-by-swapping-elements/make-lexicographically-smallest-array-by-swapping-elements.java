import java.util.Arrays;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Store pairs of (value, original_index)
        int[][] sortedPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedPairs[i][0] = nums[i];
            sortedPairs[i][1] = i;
        }
        
        // Sort elements by their values
        Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        while (i < n) {
            int j = i + 1;
            // Extend the connected component
            while (j < n && sortedPairs[j][0] - sortedPairs[j - 1][0] <= limit) {
                j++;
            }
            
            // Collect the original indices belonging to this group
            int[] groupIndices = new int[j - i];
            for (int k = i; k < j; k++) {
                groupIndices[k - i] = sortedPairs[k][1];
            }
            Arrays.sort(groupIndices);
            
            // Assign the smallest values to the lowest index positions
            for (int k = 0; k < groupIndices.length; k++) {
                result[groupIndices[k]] = sortedPairs[i + k][0];
            }
            
            i = j;
        }
        
        return result;
    }
}