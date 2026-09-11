class Solution {
    public int totalNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int d : digits) {
            cnt[d]++;
        }
        
        int result = 0;
        
        for (int a = 1; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 8; c += 2) {
                    int[] need = new int[10];
                    need[a]++;
                    need[b]++;
                    need[c]++;
                    
                    boolean valid = true;
                    for (int d = 0; d <= 9; d++) {
                        if (need[d] > cnt[d]) {
                            valid = false;
                            break;
                        }
                    }
                    
                    if (valid) {
                        result++;
                    }
                }
            }
        }
        
        return result;
    }
}