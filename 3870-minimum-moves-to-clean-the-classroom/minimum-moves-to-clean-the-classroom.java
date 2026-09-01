class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    cnt++;
                }
            }
        }

        int full = (1 << cnt) - 1;

        int[][] id = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                id[i][j] = -1;
            }
        }

        int z = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (classroom[i].charAt(j) == 'L') {
                    id[i][j] = z++;
                }
            }
        }

        int states = m * n * (1 << cnt) * (energy + 1);
        boolean[] vis = new boolean[states];

        int[] qr = new int[states];
        int[] qc = new int[states];
        int[] qm = new int[states];
        int[] qe = new int[states];

        int head = 0, tail = 0;

        int start = ((sr * n + sc) * (1 << cnt)) * (energy + 1) + energy;
        vis[start] = true;

        qr[tail] = sr;
        qc[tail] = sc;
        qm[tail] = 0;
        qe[tail++] = energy;

        int moves = 0;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (head < tail) {
            int size = tail - head;

            while (size-- > 0) {
                int r = qr[head];
                int c = qc[head];
                int mask = qm[head];
                int e = qe[head++];

                if (mask == full) {
                    return moves;
                }

                for (int k = 0; k < 4; k++) {
                    int a = r + dr[k];
                    int b = c + dc[k];

                    if (a < 0 || a >= m || b < 0 || b >= n) {
                        continue;
                    }

                    if (classroom[a].charAt(b) == 'X') {
                        continue;
                    }

                    if (e == 0) {
                        continue;
                    }

                    int ne = e - 1;
                    int nm = mask;

                    if (classroom[a].charAt(b) == 'L') {
                        nm |= (1 << id[a][b]);
                    }

                    if (classroom[a].charAt(b) == 'R') {
                        ne = energy;
                    }

                    int x = ((a * n + b) * (1 << cnt) + nm) * (energy + 1) + ne;

                    if (!vis[x]) {
                        vis[x] = true;
                        qr[tail] = a;
                        qc[tail] = b;
                        qm[tail] = nm;
                        qe[tail++] = ne;
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}