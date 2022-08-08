package java08_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;
//static 변수 줄임
//DFS와 BFS S2
public class Main_1260_DFS와BFS_S2 {
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st;
		ArrayList<ArrayList<Integer>> node;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		node = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			node.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			node.get(from).add(to);
			node.get(to).add(from);
		}
		for(int i=1; i<=N; i++) {
			Collections.sort(node.get(i));
		}
		
		boolean[] visited = new boolean[N+1];

		DFS(node, V, visited);
		sb.append("\n");
		
		visited = new boolean[N+1];
		BFS(node, V, visited);
		
		System.out.println(sb);
	}
	
	private static void DFS(ArrayList<ArrayList<Integer>> node, int cur, boolean[] visited) {
		if(visited[cur]) return;
		
		sb.append(cur+" ");
		visited[cur] = true;
		
		for(int next:node.get(cur)) {
			DFS(node, next, visited);
		}
	}

	private static void BFS(ArrayList<ArrayList<Integer>> node, int cur, boolean[] visited) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(cur);
		visited[cur] = true;
		sb.append(cur+" ");
		
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int next:node.get(cur)) {
				if(visited[next]) continue;
				
				
				sb.append(next+" ");
				visited[next] = true;
				q.offer(next);
			}
		}
	}
}
