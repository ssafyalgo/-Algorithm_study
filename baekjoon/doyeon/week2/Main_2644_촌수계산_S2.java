package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Family {
    int v;
    int w;
    LinkedList<Integer>[] adj;

    public Family(int v) {
        this.v = v;
        adj = new LinkedList[v + 1];
        for (int i = 0; i < v + 1; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void add(int v, int w) {
        adj[v].add(w);
    }
}

public class Main_2644_촌수계산_S2 {
    static int N, M;
    static int[] p = new int[2];
    static int result = 0;
    static ArrayList<Boolean> check = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Family f = new Family(N + 1);
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        p[0] = Integer.parseInt(st.nextToken());
        p[1] = Integer.parseInt(st.nextToken());
        check.add(false);
        check.add(false);

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            f.add(parent, child);
            f.add(child, parent);
        }
        bfs(f, p[0]);    //7
        System.out.println(result);
    }

    private static void bfs(Family f, int i) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[f.v];
        boolean find = false;

        q.offer(i);
        visited[i] = true;

        while (!q.isEmpty()) {
            int len = q.size();
            result++;

            for (int k = 0; k < len; k++) {
                int cur = q.poll();
                for (int j = 0; j < f.adj[cur].size(); j++) {
                    if (!visited[f.adj[cur].get(j)]) {
                        visited[f.adj[cur].get(j)] = true;
                        q.offer(f.adj[cur].get(j));
                    }
                    if (f.adj[cur].get(j) == p[1])
                        return;
                }
            }
        }
        if (!find) {
            result = -1;
            return;
        }
    }
}
