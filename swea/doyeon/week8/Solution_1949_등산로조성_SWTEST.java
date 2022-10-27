package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_1949_등산로조성_SWTEST {
    static int N, K, res;
    static int[][] map;
    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static List<Pos> maxNums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            maxNums = new ArrayList<>();
            int max = 0;
            res = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }
            //봉우리 저장
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) maxNums.add(new Pos(i, j));
                }
            }

            for (int i = 0; i < maxNums.size(); i++) {
                boolean [][] visited = new boolean[N][N];
                visited[maxNums.get(i).r][maxNums.get(i).c]=true;
                dfs(maxNums.get(i),1, K,map,visited);
            }
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static void dfs(Pos p, int cnt, int cutamount, int [][] map, boolean [][] visited) {
        res= Math.max(cnt,res);

        for (int i = 0; i < 4; i++) {
            int nr = p.r+dr[i];
            int nc = p.c+dc[i];
            if(!check(nr,nc))   continue;
            if(visited[nr][nc]) continue;
            if(map[p.r][p.c]>map[nr][nc]){
                visited[nr][nc]=true;
                dfs(new Pos(nr,nc),cnt+1,cutamount,map,visited);
                visited[nr][nc]=false;
            }
            else if(cutamount>0&&map[p.r][p.c]>map[nr][nc]-K){
                int val= map[nr][nc];
                map[nr][nc]=map[p.r][p.c]-1;
                visited[nr][nc]=true;
                dfs(new Pos(nr,nc),cnt+1,0,map,visited);
                visited[nr][nc]=false;
                map[nr][nc]=val;
            }
        }
    }
    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
