package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_사다리조작_G3 {
    static int N, M, H;
    static int [][] ladder;
    static boolean result=false;
    static int min=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());
        ladder=new int[H][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken())-1;
            int b=Integer.parseInt(st.nextToken())-1;
            ladder[a][b]=1;
            ladder[a][b+1]=2;
        }

        dfs(ladder,0);

        System.out.println(result?min:-1);
    }

    private static void dfs(int[][] tmp, int cnt) {
        if(cnt>3){
            return;
        }
        if(check(tmp)){
            min= Math.min(min,cnt);
            result=true;
            return;
        }
        if(cnt==3){
            return;
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N-1; j++) {
                if(tmp[i][j]!=0&&tmp[i][j+1]!=0)  continue;    //해당 위치에 이미 가로선이 있으면
                if(tmp[i][j]==0&&tmp[i][j+1]==0) {
                    tmp[i][j] = 1;
                    tmp[i][j + 1] = 2;
                    dfs(tmp, cnt + 1);
                    tmp[i][j] = 0;
                    tmp[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean check(int[][] tmp) {
        for (int i = 0; i < N; i++) {
            if (play(i, tmp) != i) {
                return false;
            }
        }
        return true;
    }

    private static int play(int n,int[][] tmp) {
        int result=0;
        int depth=0;
        int s=n;

        while(true){
            if(depth<H && tmp[depth][s]==0){    //현재 위치가 0이면 이어진 가로선이 없기 때문에 밑으로
                depth++;
            }
            else if(tmp[depth][s]!=0){  //현재 위치에 가로선이 있으면
                if(s-1>=0 && tmp[depth][s]==2){  //왼쪽으로 이동
                    s--;
                }
                else if(s+1<N && tmp[depth][s]==1) { //오른쪽으로 이동
                    s++;
                }
                depth++;
            }

            if(depth==H){
                result=s;
                break;
            }
        }

        return result;
    }
}