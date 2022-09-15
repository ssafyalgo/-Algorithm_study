package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136_색종이붙이기_G2 {
    static int [] balance;
    static int [][] board;
    static int mincount=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        balance=new int[6];
        board=new int[10][10];

        for (int i = 0; i <= 5; i++) {
            balance[i]=5;
        }

        for (int i = 0; i < 10; i++) {
            StringTokenizer st =new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < 10; j++) {
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,0);

        System.out.println(mincount==Integer.MAX_VALUE?-1:mincount);
    }

    private static void dfs(int r, int c, int cnt) {
        if(r==9&&c>9){
            mincount= Math.min(mincount,cnt);
            return;
        }
        if(cnt>=mincount){
            return;
        }
        if(c>9){
            dfs(r+1,0,cnt);
            return;
        }

        //현재 위치가 1이면
        if(check(r,c)&&board[r][c]==1){
            for (int i = 5; i >= 1; i--) {
                if(balance[i]>0&&isattach(r,c,i)){  //붙일 수 있는 경우
                    balance[i]--;
                    statechange(r,c,i,0);
                    dfs(r,c+1,cnt+1);
                    balance[i]++;
                    statechange(r,c,i,1);
                }
            }
        }
        else{
            dfs(r,c+1,cnt);
        }
    }

    private static void statechange(int r, int c, int size, int state) {
        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                board[i][j]=state;
            }
        }
    }

    private static boolean isattach(int r, int c, int size) {
        if(r+size>10||c+size>10)    return false;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (board[i][j] != 1)
                    return false;
            }
        }
        return true;
    }

    private static boolean check(int r, int c) {
        return r>=0&&r<10&&c>=0&&c<10;
    }
}
