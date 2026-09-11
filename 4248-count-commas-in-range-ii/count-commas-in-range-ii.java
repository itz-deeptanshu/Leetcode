class Solution {
    public long countCommas(long n) {
        long total = 0;
        long threshold = 1000;
        
        // Check for each comma threshold: 1,000; 1,000,000; 1,000,000,000; etc.
        while (n >= threshold) {
            total += n - threshold + 1;
            threshold *= 1000;
        }
        
        return total;
    }
}