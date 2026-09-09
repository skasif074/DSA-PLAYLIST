class Solution {
    public long countCommas(long n) {
        String s = Long.toString(n);
        int len = s.length();
        long total = 0;
        long pow10 = 1; // 10^(d-1)
        for (int d = 1; d <= len; d++) {
            long lower = pow10;
            long count;
            if (d < len) {
                long upper = pow10 * 10 - 1;
                count = upper - lower + 1;
            } else {
                count = n - lower + 1;
            }
            long commasPer = (d - 1) / 3;
            total += commasPer * count;
            pow10 *= 10;
        }
        return total;
    }
}