import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> {
            int ra = intervals.get(a).get(1), rb = intervals.get(b).get(1);
            if (ra != rb) return Integer.compare(ra, rb);
            int la = intervals.get(a).get(0), lb = intervals.get(b).get(0);
            if (la != lb) return Integer.compare(la, lb);
            return Integer.compare(a, b);
        });

        int[] sl = new int[n], sr = new int[n], sw = new int[n], sidx = new int[n];
        for (int i = 0; i < n; i++) {
            int oi = order[i];
            sl[i] = intervals.get(oi).get(0);
            sr[i] = intervals.get(oi).get(1);
            sw[i] = intervals.get(oi).get(2);
            sidx[i] = oi;
        }

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = lowerBound(sr, sl[i]);
        }

        int K = 4;
        long[][] score = new long[K + 1][n + 1];
        int[][][] list = new int[K + 1][n + 1][];
        for (int k = 0; k <= K; k++) {
            list[k][0] = new int[0];
            score[k][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            list[0][i] = new int[0];
            score[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            int curW = sw[i - 1], curIdx = sidx[i - 1];
            int pi = p[i - 1];
            for (int k = 1; k <= K; k++) {
                long score1 = score[k][i - 1];
                int[] list1 = list[k][i - 1];

                long score2 = score[k - 1][pi] + curW;
                int[] list2 = insertSorted(list[k - 1][pi], curIdx);

                if (better(score2, list2, score1, list1)) {
                    score[k][i] = score2;
                    list[k][i] = list2;
                } else {
                    score[k][i] = score1;
                    list[k][i] = list1;
                }
            }
        }

        return list[K][n];
    }

    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private int[] insertSorted(int[] base, int val) {
        int n = base.length;
        int[] res = new int[n + 1];
        int pos = 0;
        while (pos < n && base[pos] < val) {
            res[pos] = base[pos];
            pos++;
        }
        res[pos] = val;
        for (int j = pos; j < n; j++) {
            res[j + 1] = base[j];
        }
        return res;
    }

    private boolean better(long scoreA, int[] listA, long scoreB, int[] listB) {
        if (scoreA != scoreB) return scoreA > scoreB;
        int len = Math.min(listA.length, listB.length);
        for (int t = 0; t < len; t++) {
            if (listA[t] != listB[t]) return listA[t] < listB[t];
        }
        return listA.length < listB.length;
    }
}