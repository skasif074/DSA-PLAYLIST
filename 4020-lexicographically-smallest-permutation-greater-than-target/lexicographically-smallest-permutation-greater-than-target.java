class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = target.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        
        int[] cur = cnt.clone();
        int bestI = -1;
        int[] bestCnt = null;
        
        for (int i = 0; i < n; i++) {
            int t = target.charAt(i) - 'a';
            boolean feasible = false;
            for (int c = t + 1; c < 26; c++) {
                if (cur[c] > 0) { feasible = true; break; }
            }
            if (feasible) {
                bestI = i;
                bestCnt = cur.clone();
            }
            if (cur[t] > 0) {
                cur[t]--;
            } else {
                break;
            }
        }
        
        if (bestI == -1) return "";
        
        StringBuilder sb = new StringBuilder();
        sb.append(target, 0, bestI);
        
        int t = target.charAt(bestI) - 'a';
        int chosen = -1;
        for (int c = t + 1; c < 26; c++) {
            if (bestCnt[c] > 0) { chosen = c; break; }
        }
        sb.append((char) ('a' + chosen));
        bestCnt[chosen]--;
        
        for (int c = 0; c < 26; c++) {
            for (int k = 0; k < bestCnt[c]; k++) {
                sb.append((char) ('a' + c));
            }
        }
        
        return sb.toString();
    }
}