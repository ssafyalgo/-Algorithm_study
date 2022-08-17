package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_9372_상근이의여행_S4 {

	static int N, M, cnt;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			cnt = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			visited = new boolean[N + 1];
			dfs(1);
			System.out.println(cnt);
		}
	}

	private static void dfs(int i) {
		visited[i] = true;
		
		for(int v : graph.get(i)) {
			if (!visited[v]) {
				cnt++;
				dfs(v);
			}
		}
	}

}
