class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int oddIdx = -1, oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                oddCount++;
                oddIdx = i;
            }
        }
        if ((n % 2 == 0 && oddCount != 0) || (n % 2 == 1 && oddCount != 1)) return "";

        int[] half = cnt.clone();
        char mid = 0;
        if (n % 2 == 1) {
            mid = (char) ('a' + oddIdx);
            half[oddIdx]--;
        }
        for (int i = 0; i < 26; i++) half[i] /= 2;

        int h = n / 2;
        int[][] prefix = new int[h + 1][26];
        for (int i = 0; i < h; i++) {
            prefix[i + 1] = prefix[i].clone();
            prefix[i + 1][target.charAt(i) - 'a']++;
        }

        int maxI = 0;
        for (int i = 0; i <= h; i++) {
            boolean ok = true;
            for (int j = 0; j < 26; j++) {
                if (prefix[i][j] > half[j]) { ok = false; break; }
            }
            if (ok) maxI = i;
            else break;
        }

        if (maxI == h) {
            String firstHalf = target.substring(0, h);
            StringBuilder sb = new StringBuilder(firstHalf);
            if (mid != 0) sb.append(mid);
            sb.append(new StringBuilder(firstHalf).reverse());
            String candidate = sb.toString();
            if (candidate.compareTo(target) > 0) return candidate;
        }

        for (int i = Math.min(maxI, h - 1); i >= 0; i--) {
            int[] remaining = new int[26];
            for (int j = 0; j < 26; j++) remaining[j] = half[j] - prefix[i][j];

            int t = target.charAt(i) - 'a';
            int pick = -1;
            for (int c = t + 1; c < 26; c++) {
                if (remaining[c] > 0) { pick = c; break; }
            }
            if (pick == -1) continue;

            remaining[pick]--;
            StringBuilder tail = new StringBuilder();
            for (int c = 0; c < 26; c++) {
                for (int k = 0; k < remaining[c]; k++) tail.append((char) ('a' + c));
            }

            StringBuilder firstHalf = new StringBuilder();
            firstHalf.append(target, 0, i);
            firstHalf.append((char) ('a' + pick));
            firstHalf.append(tail);

            StringBuilder result = new StringBuilder(firstHalf);
            if (mid != 0) result.append(mid);
            result.append(new StringBuilder(firstHalf).reverse());
            return result.toString();
        }

        return "";
    }
}