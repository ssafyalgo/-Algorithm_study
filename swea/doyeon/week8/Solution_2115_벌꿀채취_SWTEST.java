package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취_SWTEST {
    static int N, M, C, res;
    static int[][] map;
    static boolean[][] visited;
    static List<Pos> work1, work2;
    static class Pos {
        int r, c;
        boolean use;
        public Pos(int r, int c, boolean use) {
            this.r = r;
            this.c = c;
            this.use = use;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            res = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    honey(i, j);
                }
            }
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void honey(int r, int c) {
        int tot_1 = 0, tot_2 = 0;
        work1 = new ArrayList<>();
        work2 = new ArrayList<>();
        visited = new boolean[N][N];
        //첫 번째 일꾼의 채취한 벌꿀 양
        for (int j = c; j < c + M; j++) {
            visited[r][j] = true;
            tot_1 += map[r][j];
            work1.add(new Pos(r, j, false));
        }
        //tot_1벌꿀의 양이 채취할 수 있는 최대 양보다 큰 지 확인
        if (tot_1 > C) {
            max = 0;
            gather(work1, 0, 0);
            tot_1 = max;
        } else {
            tot_1 = 0;
            for (int i = 0; i < work1.size(); i++) {
                int d = map[work1.get(i).r][work1.get(i).c];
                tot_1 += d * d;
            }
        }
        //두 번째 일꿀이 채취한 벌꿀 양
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                work2.clear();
                if (j + M > N) continue;
                if (visited[i][j]||visited[i][j+(M-1)]) continue;
                tot_2 = 0;
                for (int k = 0; k < M; k++) {
                    tot_2 += map[i][j + k];
                    work2.add(new Pos(i, j + k, false));
                }
                if (tot_2 > C) {
                    max = 0;
                    gather(work2, 0, 0);
                    tot_2 = max;
                } else {
                    tot_2 = 0;
                    for (int k = 0; k < work2.size(); k++) {
                        int d = map[work2.get(k).r][work2.get(k).c];
                        tot_2 += d * d;
                    }
                }
                //일꾼1,2의 총 채취 벌꿀 양 계산
                int total = tot_1 + tot_2;
                res = Math.max(res, total);
            }
        }
    }

    static int max = Integer.MIN_VALUE;
    private static void gather(List<Pos> work, int total, int money) {
        if (money > max) {
            max = Math.max(max, money);
        }

        for (int i = 0; i < work.size(); i++) {
            if (work.get(i).use) continue;
            if (total + map[work.get(i).r][work.get(i).c] <= C) {
                work.get(i).use = true;
                int d = map[work.get(i).r][work.get(i).c];
                gather(work, total + d, money + (d * d));
                work.get(i).use = false;
            }
        }
    }
}
