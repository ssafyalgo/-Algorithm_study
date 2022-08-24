import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] data;

    static boolean unionSet(int a, int b){
        if(findSet(a)==findSet(b)) return false;
        else data[findSet(a)] = findSet(b);
        return true;
    }

    static int findSet(int a){
        if(a == data[a]) return a;
        return data[a] = findSet(data[a]);
    }

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while(tc--!=0){
            StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(stz.nextToken());//국가수
            int M = Integer.parseInt(stz.nextToken());//비행기의종류
            data = new int[N+1];
            for(int i=0;i<=N; i++){
                data[i]=i;
            }
            int cnt =0;
            for(int i=0; i<M; i++){
                stz = new StringTokenizer(br.readLine(), " ");
                int A = Integer.parseInt(stz.nextToken());
                int B = Integer.parseInt(stz.nextToken());
                if(unionSet(A,B))cnt++;
            }
            System.out.println(cnt);
        }
    }
}
