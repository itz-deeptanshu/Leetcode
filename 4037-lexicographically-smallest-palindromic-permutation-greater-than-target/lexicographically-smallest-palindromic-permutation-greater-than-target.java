class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }

        // A valid palindrome can have at most one character with an odd frequency
        if (oddCount > 1 || (oddCount == 1 && n % 2 == 0) || (oddCount == 0 && n % 2 != 0)) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Case 1: Check if the palindrome formed by matching target[0..m-1] is strictly greater than target
        int[] tempCount = halfCount.clone();
        boolean canMatchFirstHalf = true;
        for (int i = 0; i < m; i++) {
            int c = target.charAt(i) - 'a';
            if (--tempCount[c] < 0) {
                canMatchFirstHalf = false;
                break;
            }
        }

        if (canMatchFirstHalf) {
            char[] p = new char[n];
            for (int i = 0; i < m; i++) {
                p[i] = target.charAt(i);
                p[n - 1 - i] = target.charAt(i);
            }
            if (n % 2 != 0) {
                p[m] = (char) ('a' + oddChar);
            }

            String candidate = new String(p);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // Case 2: Find the longest prefix target[0..i-1] where we can place a character > target[i] at index i
        int[] prefCount = new int[26];
        int validLen = 0;
        while (validLen < m) {
            int c = target.charAt(validLen) - 'a';
            if (prefCount[c] + 1 > halfCount[c]) {
                break;
            }
            prefCount[c]++;
            validLen++;
        }

        // Try every valid split index from largest to smallest
        for (int i = validLen; i >= 0; i--) {
            if (i < m) {
                int targetChar = target.charAt(i) - 'a';
                int[] remCount = new int[26];
                for (int c = 0; c < 26; c++) {
                    remCount[c] = halfCount[c] - prefCount[c];
                }

                for (int c = targetChar + 1; c < 26; c++) {
                    if (remCount[c] > 0) {
                        remCount[c]--;

                        char[] res = new char[n];
                        for (int j = 0; j < i; j++) {
                            res[j] = target.charAt(j);
                        }
                        res[i] = (char) ('a' + c);

                        int idx = i + 1;
                        for (int ch = 0; ch < 26; ch++) {
                            while (remCount[ch] > 0) {
                                res[idx++] = (char) ('a' + ch);
                                remCount[ch]--;
                            }
                        }

                        if (n % 2 != 0) {
                            res[m] = (char) ('a' + oddChar);
                        }

                        for (int j = 0; j < m; j++) {
                            res[n - 1 - j] = res[j];
                        }

                        return new String(res);
                    }
                }
            }

            if (i > 0) {
                prefCount[target.charAt(i - 1) - 'a']--;
            }
        }

        return "";
    }
}