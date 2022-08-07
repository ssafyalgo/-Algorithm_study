package algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_DFS와BFS_S2 {

	static int N;
	static int M;
	static int V;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		
		//	작은 노드부터 방문하기 위한 정렬
		for (int i = 0; i <= N; i++) {		
			Collections.sort(graph.get(i));
		}
		
		dfs(V);
		System.out.println();

		Arrays.fill(visited, false);
		bfs(V);
	}

	private static void dfs(int x) {
		// 현재 노드를 방문 처리
		visited[x] = true;
		System.out.print(x + " ");
		// 현재 노드와 연결된 다른 노드를 재귀적으로 방문
		for (int value : graph.get(x)) {
			if (!visited[value])
				dfs(value);
		}
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		// 현재 노드를 방문 처리
		visited[start] = true;
		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			// 큐에서 하나의 원소를 뽑아 출력
			int x = q.poll();
			System.out.print(x + " ");
			// 해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
			for (int value : graph.get(x)) {
				if (!visited[value]) {
					q.offer(value);
					visited[value] = true;
				}
			}
		}
	}
}