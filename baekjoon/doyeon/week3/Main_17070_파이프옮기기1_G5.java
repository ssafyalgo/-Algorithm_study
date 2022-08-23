package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17070_파이프옯기기1_G5 {
    static int N;
    static int [][] house;
    static int res;
    static int state;
    static int [] paral_adr={0,0};
    static int [] paral_adc={1,1};
    static int [] paral_bdr={0,1};
    static int [] paral_bdc={1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        house=new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 2; i++) {
            int a_nr=0+paral_adr[i];
            int a_nc=0+paral_adc[i];
            int b_nr=0+paral_bdr[i];
            int b_nc=1+paral_bdc[i];
            if(check(a_nr,a_nc)&&check(b_nr,b_nc)&&house[a_nr][a_nc]==0&&house[b_nr][b_nc]==0)
            toEnd(new int[][]{{0,0},{0,1}});    //가로 한번, 대각선 한번
        }

    }

    private static boolean check(int r, int c) {
        return (r>=0&&r<N&&c>=0&&c<N);
    }

    private static void toEnd(int[][] start) {
        Queue<int[][]> q = new LinkedList<>();

        q.offer(start);

        while (!q.isEmpty()){
            int [][] cur=q.poll();

        }
    }
}
