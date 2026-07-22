/*You are given a binary string s of length n, where:

'1' represents an active section.
'0' represents an inactive section.
You can perform at most one trade to maximize the number of active sections in s. In a trade, you:

Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
Additionally, you are given a 2D array queries, where queries[i] = [li, ri] represents a substring s[li...ri].

For each query, determine the maximum possible number of active sections in s after making the optimal trade on the substring s[li...ri].

Return an array answer, where answer[i] is the result for queries[i].

Note

For each query, treat s[li...ri] as if it is augmented with a '1' at both ends, forming t = '1' + s[li...ri] + '1'. The augmented '1's do not contribute to the final count.
The queries are independent of each other.
 

Example 1:

Input: s = "01", queries = [[0,1]]

Output: [1]

Explanation:

Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Example 2:

Input: s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]

Output: [4,3,1,1]

Explanation:

Query [0, 3] → Substring "0100" → Augmented to "101001"
Choose "0100", convert "0100" → "0000" → "1111".
The final string without augmentation is "1111". The maximum number of active sections is 4.

Query [0, 2] → Substring "010" → Augmented to "10101"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "1110". The maximum number of active sections is 3.

Query [1, 3] → Substring "100" → Augmented to "11001"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Query [2, 3] → Substring "00" → Augmented to "1001"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Example 3:

Input: s = "1000100", queries = [[1,5],[0,6],[0,4]]

Output: [6,7,2]

Explanation:

Query [1, 5] → Substring "00010" → Augmented to "1000101"
Choose "00010", convert "00010" → "00000" → "11111".
The final string without augmentation is "1111110". The maximum number of active sections is 6.

Query [0, 6] → Substring "1000100" → Augmented to "110001001"
Choose "000100", convert "000100" → "000000" → "111111".
The final string without augmentation is "1111111". The maximum number of active sections is 7.

Query [0, 4] → Substring "10001" → Augmented to "1100011"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

Example 4:

Input: s = "01010", queries = [[0,3],[1,4],[1,3]]

Output: [4,4,2]

Explanation:

Query [0, 3] → Substring "0101" → Augmented to "101011"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "11110". The maximum number of active sections is 4.

Query [1, 4] → Substring "1010" → Augmented to "110101"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "01111". The maximum number of active sections is 4.

Query [1, 3] → Substring "101" → Augmented to "11011"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

 

Constraints:

1 <= n == s.length <= 105
1 <= queries.length <= 105
s[i] is either '0' or '1'.
queries[i] = [li, ri]
0 <= li <= ri < n*/



import java.util.*;

class Solution {
    class SegTree {
        int[] maxZ, minO, maxZSum;
        int n;

        public SegTree(int[] Z, int[] O, int[] ZSum) {
            n = Z.length;
            if (n == 0) return;
            maxZ = new int[4 * n];
            minO = new int[4 * Math.max(1, O.length)];
            maxZSum = new int[4 * Math.max(1, ZSum.length)];
            

            Arrays.fill(minO, Integer.MAX_VALUE);
            Arrays.fill(maxZSum, Integer.MIN_VALUE);
            
            build(1, 0, n - 1, Z, O, ZSum);
        }

        private void build(int node, int start, int end, int[] Z, int[] O, int[] ZSum) {
            if (start == end) {
                maxZ[node] = Z[start];
                if (start < O.length) minO[node] = O[start];
                if (start < ZSum.length) maxZSum[node] = ZSum[start];
                return;
            }
            int mid = (start + end) / 2;
            int leftChild = 2 * node, rightChild = 2 * node + 1;
            build(leftChild, start, mid, Z, O, ZSum);
            build(rightChild, mid + 1, end, Z, O, ZSum);

            maxZ[node] = Math.max(maxZ[leftChild], maxZ[rightChild]);
            minO[node] = Math.min(minO[leftChild], minO[rightChild]);
            maxZSum[node] = Math.max(maxZSum[leftChild], maxZSum[rightChild]);
        }

        public int queryMaxZ(int node, int start, int end, int l, int r) {
            if (r < start || end < l || l > r) return Integer.MIN_VALUE;
            if (l <= start && end <= r) return maxZ[node];
            int mid = (start + end) / 2;
            return Math.max(queryMaxZ(2 * node, start, mid, l, r), queryMaxZ(2 * node + 1, mid + 1, end, l, r));
        }

        public int queryMinO(int node, int start, int end, int l, int r) {
            if (r < start || end < l || l > r) return Integer.MAX_VALUE;
            if (l <= start && end <= r) return minO[node];
            int mid = (start + end) / 2;
            return Math.min(queryMinO(2 * node, start, mid, l, r), queryMinO(2 * node + 1, mid + 1, end, l, r));
        }

        public int queryMaxZSum(int node, int start, int end, int l, int r) {
            if (r < start || end < l || l > r) return Integer.MIN_VALUE;
            if (l <= start && end <= r) return maxZSum[node];
            int mid = (start + end) / 2;
            return Math.max(queryMaxZSum(2 * node, start, mid, l, r), queryMaxZSum(2 * node + 1, mid + 1, end, l, r));
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        char[] c = s.toCharArray();
        int totalOnes = 0;
        for (char ch : c) {
            if (ch == '1') totalOnes++;
        }

        int[] nextZero = new int[n];
        int[] prevZero = new int[n];
        int[] zeroBlockIdx = new int[n];
        
        int lastZ = -1;
        for (int i = 0; i < n; i++) {
            if (c[i] == '0') lastZ = i;
            prevZero[i] = lastZ;
        }
        
        int nxtZ = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (c[i] == '0') nxtZ = i;
            nextZero[i] = nxtZ;
        }

        List<int[]> zBlocksList = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (c[i] == '0') {
                int j = i;
                while (j < n && c[j] == '0') j++;
                zBlocksList.add(new int[]{i, j - 1});
                for (int k = i; k < j; k++) zeroBlockIdx[k] = zBlocksList.size() - 1;
                i = j;
            } else {
                i++;
            }
        }

        int K = zBlocksList.size();
        List<Integer> ans = new ArrayList<>();
        
        if (K == 0) {
            for (int[] q : queries) ans.add(totalOnes);
            return ans;
        }

        int[] Z = new int[K];
        for (int k = 0; k < K; k++) {
            Z[k] = zBlocksList.get(k)[1] - zBlocksList.get(k)[0] + 1;
        }

        int[] O = new int[Math.max(1, K - 1)];
        for (int k = 0; k < K - 1; k++) {
            O[k] = zBlocksList.get(k + 1)[0] - zBlocksList.get(k)[1] - 1;
        }

        int[] ZSum = new int[Math.max(1, K - 1)];
        for (int k = 0; k < K - 1; k++) {
            ZSum[k] = Z[k] + Z[k + 1];
        }

        SegTree tree = new SegTree(Z, O, ZSum);

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int p1 = nextZero[l], p2 = prevZero[r];
            if (p1 == -1 || p1 > r || p1 >= p2) {
                ans.add(totalOnes);
                continue;
            }

            int k1 = zeroBlockIdx[p1], k2 = zeroBlockIdx[p2];
            if (k1 == k2) {
                ans.add(totalOnes);
                continue;
            }

            int zFirst = zBlocksList.get(k1)[1] - p1 + 1;
            int zLast = p2 - zBlocksList.get(k2)[0] + 1;
            
            int zMax = Math.max(zFirst, zLast);
            if (k1 + 1 <= k2 - 1) {
                zMax = Math.max(zMax, tree.queryMaxZ(1, 0, K - 1, k1 + 1, k2 - 1));
            }

            int oMin = tree.queryMinO(1, 0, K - 1, k1, k2 - 1);
            
            int maxSum = Integer.MIN_VALUE;
            if (k2 == k1 + 1) {
                maxSum = zFirst + zLast;
            } else {
                maxSum = Math.max(maxSum, zFirst + Z[k1 + 1]);
                maxSum = Math.max(maxSum, Z[k2 - 1] + zLast);
                if (k1 + 1 <= k2 - 2) {
                    maxSum = Math.max(maxSum, tree.queryMaxZSum(1, 0, K - 1, k1 + 1, k2 - 2));
                }
            }

            int maxGain = Math.max(maxSum, zMax - oMin);
            ans.add(totalOnes + Math.max(0, maxGain));
        }

        return ans;
    }
}
