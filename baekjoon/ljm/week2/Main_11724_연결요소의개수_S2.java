package java08_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11724_연결요소의개수_S2 {
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to);
			list.get(to).add(from);
		}

		visited = new boolean[N + 1];
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (visited[i]) {
				continue;
			}
			cnt++;
			BFS(i);
		}

		System.out.println(cnt);
	}

	private static void BFS(int i) {
		Queue<Integer> q = new LinkedList<>();
		visited[i] = true;
		q.offer(i);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int next : list.get(cur)) {
				if (visited[next])
					continue;

				visited[next] = true;
				q.offer(next);
			}
		}
	}
}
