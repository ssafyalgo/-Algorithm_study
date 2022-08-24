import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] ans= new int[10];
    static int[] visit = new int[10];

    static void perm(int depth, int N, int R){
        if(depth==R){
            for(int i=0; i<R; i++){
                System.out.print(ans[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=0 ;i<N; i++){
            if(visit[i]==0){
                ans[depth] = i+1;
                visit[i]=1;
                perm(depth+1, N,R);
                visit[i]=0;
            }
        }
    }

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        perm(0,N,M);
    }
}
