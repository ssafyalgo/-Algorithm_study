import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260 {
    static int [][] map;
    static boolean [] visited;
    static int N,M,V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());
        map=new int[N][N];
        visited=new boolean[N];

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine()," ");
            int r=Integer.parseInt(st.nextToken())-1;
            int c=Integer.parseInt(st.nextToken())-1;
            map[r][c]=1;
            map[c][r]=1;
        }

        dfs(V-1);
        System.out.println();
        for (int i = 0; i < N; i++) {
            visited[i]=false;
        }
        bfs(V-1);
    }

    private static void bfs(int v) {
        Queue<Integer> q=new LinkedList<>();

        q.add(v);
        visited[v]=true;
        while (!q.isEmpty()){
            v=q.remove();
            System.out.print((v+1)+" ");
            for (int i = 0; i < N; i++) {
                if(!visited[i]&&map[v][i]==1){
                    q.add(i);
                    visited[i]=true;
                }
            }
        }
    }

    private static void dfs(int v) {
        visited[v]=true;
        System.out.print((v+1)+" ");

        for (int i = 0; i < N; i++) {
            if(!visited[i]&&map[v][i]==1){
                dfs(i);
            }
        }
    }
}
