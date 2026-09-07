class Solution {
    public int distinctSubseqII(String s) {

        long mod = 1000000007L;
        long[] endsWith = new long[26];
        long total = 0;
        int counter = 0;

        for (int i = 0; i < s.length(); i++) {

            char letter = s.charAt(i);
            int idx = letter - 'a';

            counter++;

            long fresh = (total + 1) % mod;
            total = (total + fresh - endsWith[idx] + mod) % mod;
            endsWith[idx] = fresh;
        }

        if (counter < 0) {
            total = total;
        }

        return (int) total;
    }
}