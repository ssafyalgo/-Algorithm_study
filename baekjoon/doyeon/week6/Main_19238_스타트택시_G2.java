package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_19238_스타트택시_G2 {
    static class Pos implements Comparable<Pos>, Cloneable{
        int sr,sc, er,ec;
        int depth;
        public Pos(int sr, int sc, int er, int ec, int depth) {
            this.sr = sr;
            this.sc = sc;
            this.er = er;
            this.ec = ec;
            this.depth = depth;
        }
        @Override
        public int compareTo(Pos o) {
            if(this.depth==o.depth) {
                if (this.sr == o.sr)
                    return Integer.compare(this.sc, o.sc);
                return Integer.compare(this.sr, o.sr);
            }
            return Integer.compare(this.depth,o.depth);
        }
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
    static int N,M,fuel,res=-1;
    static int [][] map;
    static Pos driver;
    static PriorityQueue<Pos> client;
    static PriorityQueue<Pos> temp;
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        fuel=Integer.parseInt(st.nextToken());
        map = new int[N][N];
        client=new PriorityQueue<>();
        temp=new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine()," ");
        driver=new Pos(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,0,0,0);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int sr=Integer.parseInt(st.nextToken())-1;
            int sc =Integer.parseInt(st.nextToken())-1;
            int er=Integer.parseInt(st.nextToken())-1;
            int ec=Integer.parseInt(st.nextToken())-1;
            client.offer(new Pos(sr,sc,er,ec,Integer.MAX_VALUE));
        }
        
        while(true){
            if(M==0) {
                res = fuel;
                break;
            }
            bfs();
            order();
            Pos c=temp.peek();
            int d = destination(c);
            if(d==-1){
                res=-1;
                break;
            }
            //다음 출발지로 못가는 경우
            if(c.depth>=fuel)  {
                res=-1;
                break;
            }
            //다음 목적지로 못가는 경우
            if(fuel-c.depth-d<0)  {
                res=-1;
                break;
            }
            c.sr=-1;
            c.sc=-1;
            fuel=fuel-c.depth-d+(d*2);  //연료
            driver.sr=c.er; //운전자 위치 변경
            driver.sc=c.ec;
            M--;
            while(!temp.isEmpty()){
                Pos p=temp.poll();
                p.depth=Integer.MAX_VALUE;
                client.offer(p);
            }
        }
        System.out.println(res);
    }
    private static void order() {
        while(!client.isEmpty()){
            temp.offer(client.poll());
        }
    }
    static int [] dr = {-1, 0, 0, 1};
    static int [] dc = {0, -1, 1, 0};
    private static int destination(Pos c) throws CloneNotSupportedException {
        Queue<Pos> q=new LinkedList<>();
        boolean visited [][]=new boolean[N][N];
        boolean stop = false;
        Pos p=(Pos)c.clone();
        p.depth=0;
        q.offer(p);
        int d=-1;   //큐에 넣을 위치가 없는 경우가 있음

        while (!q.isEmpty()) {
            Pos now = q.poll();
            if (now.sr == now.er && now.sc == now.ec) {
                d = now.depth;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = now.sr + dr[i];
                int nc = now.sc + dc[i];
                if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    q.offer(new Pos(nr, nc, now.er, now.ec, now.depth + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        return d;
    }

    private static void bfs() {
        Queue<Pos> q = new LinkedList<>();
        boolean visited [][]=new boolean[N][N];
        boolean stop = false;
        q.offer(driver);

        while (!q.isEmpty()){
            Pos now = q.poll();
            for (Pos c:client) {
                if((c.sr!=-1&&c.sc!=-1)&&now.depth>c.depth)   stop=true;
            }
            if(stop) break;
            for (Pos c:client) {
                if(now.sr==c.sr&&now.sc==c.sc) {
                    c.depth = now.depth;
                }
            }
            for (int i = 0; i < 4; i++) {
                int nr = now.sr + dr[i];
                int nc = now.sc + dc[i];
                if(check(nr, nc)&&!visited[nr][nc]&&map[nr][nc]==0){
                    q.offer(new Pos(nr, nc, 0,0, now.depth+1));
                    visited[nr][nc]=true;
                }
            }
        }
    }
    private static boolean check(int nr, int nc) {
        return nr>=0&&nr<N&&nc>=0&&nc<N;
    }
}