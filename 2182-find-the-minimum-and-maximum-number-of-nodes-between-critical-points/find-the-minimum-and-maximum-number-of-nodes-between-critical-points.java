class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Less than 3 nodes cannot have any critical points
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        
        int firstIndex = -1;
        int prevIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        int index = 1; // 0-indexed position of 'curr'

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if curr is a local maxima or local minima
            boolean isMaxima = (curr.val > prev.val) && (curr.val > next.val);
            boolean isMinima = (curr.val < prev.val) && (curr.val < next.val);

            if (isMaxima || isMinima) {
                if (firstIndex == -1) {
                    firstIndex = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevIndex);
                }
                prevIndex = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // If fewer than 2 critical points were found
        if (firstIndex == -1 || firstIndex == prevIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevIndex - firstIndex;
        return new int[]{minDistance, maxDistance};
    }
}