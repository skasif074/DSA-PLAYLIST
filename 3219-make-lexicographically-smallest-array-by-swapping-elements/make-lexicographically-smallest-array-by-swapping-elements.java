class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] p = new int[n][2];
        for (int i = 0; i < n; i++) {
            p[i][0] = nums[i];
            p[i][1] = i;
        }

        Arrays.sort(p, (x, y) -> x[0] - y[0]);

        int[] res = new int[n];
        List<Integer> q = new ArrayList<>();
        List<Integer> r = new ArrayList<>();

        int m = 0;
        while (m < n) {
            q.clear();
            r.clear();

            q.add(p[m][1]);
            r.add(p[m][0]);

            int k = m;
            while (k + 1 < n && p[k + 1][0] - p[k][0] <= limit) {
                k++;
                q.add(p[k][1]);
                r.add(p[k][0]);
            }

            Collections.sort(q);

            for (int t = 0; t < q.size(); t++) {
                res[q.get(t)] = r.get(t);
            }

            m = k + 1;
        }

        return res;
    }
}