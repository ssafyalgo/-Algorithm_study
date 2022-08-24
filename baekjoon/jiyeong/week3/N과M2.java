import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] visit = new int[10];

    static void perm2(int depth, int N, int cnt, int M){
        if(depth==N){
            if(cnt==M){
                //완성, 출력하기
                for(int i=0; i<N; i++){
                    if(visit[i]==1){
                        System.out.print(i+1+" ");
                    }
                }
                System.out.println();
            }
            return;
        }
        
        visit[depth] = 1;
        perm2(depth + 1, N, cnt+1, M);
        visit[depth] = 0;
        perm2(depth + 1, N,cnt, M);
    }

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        perm2(0,N,0,M);
    }
}
