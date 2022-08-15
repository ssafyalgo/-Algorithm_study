package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Character.Subset;
import java.util.StringTokenizer;

public class Main_1182_부분수열의합_S2 {
    static int result;
    static int[] nums;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        nums=new int[N];
        visited=new boolean[N];
        st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            nums[i]=Integer.parseInt(st.nextToken());
        }
        subset(0,0,S,N);
        System.out.println(result);
    }

    private static void subset(int cnt, int total,int s,int n) {
        if(cnt==n){
            int t=0;
            for (int i = 0; i < nums.length; i++) {
                if(visited[i])  t++;
            }
            if(total==s&&t>0)
                result++;
            return;
        }

        visited[cnt]=true;
        subset(cnt+1,total+nums[cnt],s,n);
        visited[cnt]=false;
        subset(cnt+1,total,s,n);
    }
}
