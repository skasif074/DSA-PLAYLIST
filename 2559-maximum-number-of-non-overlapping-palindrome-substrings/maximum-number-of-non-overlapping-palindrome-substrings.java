class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (i >= k && isPal(arr, i - k, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }
            if (i > k && isPal(arr, i - k - 1, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }
        
        return dp[n];
    }
    
    private boolean isPal(char[] arr, int l, int r) {
        while (l < r) {
            if (arr[l] != arr[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}