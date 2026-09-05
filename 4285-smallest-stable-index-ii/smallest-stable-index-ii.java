class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int a = nums.length;
        int[] b = new int[a];
        int[] c = new int[a];
        
        int d = Integer.MIN_VALUE;
        for (int e = 0; e < a; e++) {
            d = Math.max(d, nums[e]);
            b[e] = d;
        }
        
        int f = Integer.MAX_VALUE;
        for (int g = a - 1; g >= 0; g--) {
            f = Math.min(f, nums[g]);
            c[g] = f;
        }
        
        int h = 0;
        int r = -1;
        
        for (int t = 0; t < a; t++) {
            h++;
            int v = b[t] - c[t];
            
            if (v <= k) {
                r = t;
                break;
            }
        }
        
        return r;
    }
}