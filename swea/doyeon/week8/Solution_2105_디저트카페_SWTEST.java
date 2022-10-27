package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_2105_디저트카페_SWTEST {
    static int N,res;
    static int [][] map;
    static boolean [] dessert;
    static class Pos{
        int r,c,dir;
        public Pos(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N=Integer.parseInt(br.readLine());
            map=new int[N][N];
            res=Integer.MIN_VALUE;
            dessert=new boolean[101];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < N; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N - 1; j++) {
                    if(check(i+1,j+1)&&map[i][j]!=map[i+1][j+1]) {
                        dessert[map[i+1][j+1]]=true;
                        dfs(new Pos(i, j, 0), new Pos(i + 1, j + 1, 0), 1);
                        dessert[map[i+1][j+1]]=false;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(res==Integer.MIN_VALUE?-1:res).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean check(int r, int c) {
        return r>=0&&r<N&&c>=0&&c<N;
    }
    static int [] dr ={1,1,-1,-1};
    static int [] dc ={1,-1,-1,1};
    private static void dfs(Pos first,Pos now,int cnt) {
        if(now.r==first.r&&now.c==first.c){
            res=Math.max(res,cnt);
            return;
        }
        if(now.dir<3){
            in(now.r+dr[now.dir],now.c+dc[now.dir],cnt,now.dir,first);
            in(now.r+dr[now.dir+1],now.c+dc[now.dir+1],cnt,now.dir+1,first);
        }
        else
            in(now.r+dr[now.dir],now.c+dc[now.dir],cnt,3,first);
    }

    private static void in(int r, int c, int cnt, int dir, Pos first) {
        if(check(r,c)&&(!dessert[map[r][c]])) {
            dessert[map[r][c]]=true;
            dfs(first, new Pos(r, c, dir),cnt+1);
            dessert[map[r][c]]=false;
        }
    }
}
