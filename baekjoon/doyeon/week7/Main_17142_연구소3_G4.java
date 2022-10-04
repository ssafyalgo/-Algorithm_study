package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17142_연구소3_G4 {
    static int N,M,V,min,second;
    static boolean visited[];
    static boolean finish=false;
    static int[] actvirus;  //선택된 활성바이러스
    static class Pos{
        int r,c,time;
        public Pos(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    static List<Pos> virus=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        int map[][]=new int[N][N];
        min=Integer.MAX_VALUE;
        actvirus=new int[M];

        for (int i = 0; i < N; i++) {
            st =new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==2)    virus.add(new Pos(i,j,0));
            }
        }
        V=virus.size();
        visited=new boolean[V];
        if(remain(map))
            min=0;
        else
            select(map,0, 0);

        System.out.println(min==Integer.MAX_VALUE?-1:min);
    }

    private static void select(int[][] map, int cnt, int start) {
        if(cnt==M){
            second=0;
            finish=false;
            active(map,actvirus);
            return;
        }
        for (int i = start; i < V; i++) {
            if(!visited[i]) {
                visited[i]=true;
                actvirus[cnt] = i;
                select(map,cnt+1,i+1);
                visited[i]=false;
            }
        }
    }

    private static void active(int[][] map, int[] actvirus) {
        int [][] newMap=new int [N][N];
        copy(map,newMap,actvirus);
        Queue<Pos> q = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            q.offer(virus.get(actvirus[i]));
        }
        
        while(!q.isEmpty()){
            bfs(newMap, q);
            if(finish)  break;
        }
        for (int i = 0; i < M; i++) {
            Pos p = virus.get(actvirus[i]);
            newMap[p.r][p.c]=second;
        }
        if(remain(newMap))  min=Math.min(min,second);
    }

    private static boolean remain(int[][] newMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(newMap[i][j]==0)
                    return false;
            }
        }
        return true;
    }

    static int [] dr = {-1,1,0,0};
    static int [] dc = {0,0,-1,1};
    private static void bfs(int[][] newMap, Queue<Pos> q) {
        Queue<Pos> next = new LinkedList<>();

        while(!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r+dr[i];
                int nc = p.c+dc[i];
                if(check(nr,nc)&&(newMap[nr][nc]==0||newMap[nr][nc]==2)) {
                    next.add(new Pos(nr,nc,p.time+1));
                    newMap[nr][nc]=p.time+1;
                    second=p.time+1;
                }
            }
            if(second>=min){
                finish=true;
                return;
            }
            if(remain(newMap)){
                finish=true;
                return;
            }
        }
        q.addAll(next);
    }
    private static boolean check(int r, int c) {
        return r>=0&&r<N&&c>=0&&c<N;
    }
    private static void copy(int[][] map, int[][] newMap, int[] actvirus) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i],0,newMap[i],0,map[i].length);
        }
    }
}
