package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650_N과M_S3 {
    static int N,M;
    static int [] nums;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        visited=new boolean[N+1];
        nums=new int[M];
        ncr(1,0);
    }

    private static void ncr(int start, int cnt) {
        if(cnt==M){
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                sb.append(nums[i]).append(" ");
            }
            System.out.println(sb.toString());
            return;
        }
        for (int i = start; i <= N ; i++) {
            nums[cnt]=i;
            ncr(i+1,cnt+1);
        }
    }
}
