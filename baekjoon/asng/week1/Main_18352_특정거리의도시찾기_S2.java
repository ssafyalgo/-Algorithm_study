package algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18352_특정거리의도시찾기_S2 {

	static int N, M, K, X; // N:노드 수, M:간선 수, K:최소거리조건, X:출발노드
	static int[] dist;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		dist = new int[N + 1];
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			dist[i] = -1;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}
		
		bfs(X);

		boolean chk = false;
		for (int i = 1; i <= N; i++) {
			if (dist[i] == K) {
				System.out.println(i);
				chk = true;
			}
		}

		if (!chk)
			System.out.println(-1);
	}

	private static void bfs(int start) {
		dist[start] = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				if (dist[next] == -1) {
					dist[next] = dist[cur] + 1;
					q.offer(next);
				}
			}
		}
	} 
}
