package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_11724_연결요소의개수_S2 {

	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 방향 없는 그래프이므로
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		int cnt = 0;
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

	private static void dfs(int i) {
		visited[i] = true;
		
		for (int v : graph.get(i)) {
			if (!visited[v]) {
				dfs(v);
			}
		}
	}

}
