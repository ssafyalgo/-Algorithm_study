package week8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_보호필름_SWTEST {
    static int D,W,K,res;
    static int [][] film;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T=Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            res=Integer.MAX_VALUE;
            D=Integer.parseInt(st.nextToken());
            W=Integer.parseInt(st.nextToken());
            K=Integer.parseInt(st.nextToken());
            film=new int[D][W];
            visited=new boolean[D];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < W; j++) {
                    film[i][j]=Integer.parseInt(st.nextToken());    //0->A, 1->B
                }
            }
            test(0, 0,film);
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void test(int cnt, int idx,int [][] film) {
        if(cnt>=res){
            return;
        }
        if(pass(film)){
            res= Math.min(res,cnt);
            return;
        }
        for (int i = idx; i < D; i++) {
            if(!visited[i]){
                visited[i]=true;
                int [] arr = Arrays.copyOf(film[i],film[i].length);
                change(film,i,0);
                test(cnt+1,i+1,film);
                change(film,i,1);
                test(cnt+1,i+1,film);
                for (int j = 0; j < W; j++) {
                    film[i][j]=arr[j];
                }
                visited[i]=false;
            }
        }
    }

    private static void change(int[][] film,int i,int paint) {
        for (int j = 0; j < W; j++) {
            film[i][j]=paint;
        }
    }

    private static boolean pass(int [][] film) {
        boolean p=true;
        for (int i = 0; i < W; i++) {
            int d=film[0][i];
            int count=1;
            for (int j = 1; j < D; j++) {
                if(d!=film[j][i]) {
                    d = film[j][i];
                    count=1;
                }
                else count++;
                if(count==K)    break;
            }
            if(count!=K){
                p=false;
                return false;
            }
        }
        return true;
    }
}