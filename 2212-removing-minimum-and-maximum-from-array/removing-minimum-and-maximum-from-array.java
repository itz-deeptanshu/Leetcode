class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        // Let left be the smaller index and right be the larger index
        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Option 1: Remove both from the front
        int removeFromFront = right + 1;

        // Option 2: Remove both from the back
        int removeFromBack = n - left;

        // Option 3: Remove one from front, one from back
        int removeFromBoth = (left + 1) + (n - right);

        return Math.min(removeFromFront, Math.min(removeFromBack, removeFromBoth));
    }
}