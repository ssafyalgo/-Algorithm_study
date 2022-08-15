package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Graph{
    int v;
    LinkedList<Integer> [] adj;

    public Graph(int v) {
        this.v = v+1;
        adj=new LinkedList[v+1];
        for (int i = 0; i < v+1 ; i++) {
            adj[i]=new LinkedList<>();
        }
    }
    void add(int v, int w){
        adj[v].add(w);
    }
}
public class Main_11724_연결요소의개수_S2 {
    static int N, M;
    static int result;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        Graph g=new Graph(N+1);
        visited=new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine()," ");
            int v=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            g.add(v,w);
            g.add(w,v);
        }
        for (int i = 1; i < N+1; i++) {
            if(!visited[i])
                bfs(i,g);
        }
        System.out.println(result);
    }

    private static void bfs(int node,Graph g) {
        Queue<Integer> q=new LinkedList<>();

        visited[node]=true;
        q.offer(node);

        while (!q.isEmpty()){
            int cur=q.poll();

            for (int i = 0; i < g.adj[cur].size(); i++) {
                if(!visited[g.adj[cur].get(i)]){
                    visited[g.adj[cur].get(i)]=true;
                    q.offer(g.adj[cur].get(i));
                }
            }
        }
        result++;
    }
}
