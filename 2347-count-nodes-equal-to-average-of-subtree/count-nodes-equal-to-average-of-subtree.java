class Solution {
    private int matchCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchCount = 0;
        postOrder(root);
        return matchCount;
    }

    // Returns an array where index 0 is the sum, and index 1 is the node count
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }

        // Recursively get sum and count from left and right subtrees
        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int currentSum = left[0] + right[0] + node.val;
        int currentCount = left[1] + right[1] + 1;

        // Check if node's value matches the average of its subtree
        if (node.val == currentSum / currentCount) {
            matchCount++;
        }

        return new int[] {currentSum, currentCount};
    }
}