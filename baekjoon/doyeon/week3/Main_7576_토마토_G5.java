package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토_G5 {
    static int [][] tomato;
    static int N,M;
    static int day;
    static boolean state=true;
    static ArrayList<int[]> complete;
    static Queue<int []> current;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        tomato=new int[N][M];
        current = new LinkedList<>();
        complete=new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < M; j++) {
                tomato[i][j]=Integer.parseInt(st.nextToken());
                if(tomato[i][j]==1)
                    complete.add(new int[] {i,j});
                if(tomato[i][j]==0)	state=false;
            }
        }

        if (!state) {	//만약 하나라도 토마토가 익지 않았다면
            for (int i = 0; i < complete.size(); i++) {
                current.add(complete.get(i));
            }

            while (!current.isEmpty()) {
                bfs();
            }

            System.out.println(count() == 0 ? day - 1 : -1);
        }
        else
            System.out.println(0);
    }
    private static int count() {
        int not=0;

        for (int i = 0; i <N; i++) {
            for (int j = 0; j < M; j++) {
                if(tomato[i][j]==0)	not++;
            }
        }
        return not;
    }
    static int [] dr= {0,0,1,-1};
    static int [] dc= {-1,1,0,0};
    private static void bfs() {
        Queue<int[]> next = new LinkedList<>();

        while(!current.isEmpty()) {
            int [] cur = current.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0]+dr[i];
                int nc = cur[1]+dc[i];

                if(check(nr,nc)&&tomato[nr][nc]==0) {
                    next.add(new int[]{nr,nc});
                    tomato[nr][nc]=1;
                }
            }
        }
        day++;
        current.addAll(next);
    }
    private static boolean check(int r, int c) {
        return r>=0&&r<N&&c>=0&&c<M;
    }
}