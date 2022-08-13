package java08_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 *  1에서 2로 가는 간선의 수만 찾으면 됨
 * 
 */

public class Main_2644_촌수계산_S2 {
	static ArrayList<ArrayList<Integer>> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		int E = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to);
			list.get(to).add(from);
		}
		
		System.out.println(BFS(num1, num2, N));
	}
	private static int BFS(int start, int end, int n) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		visited[start] = true;
		q.offer(new int[] {start, 0});

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int next : list.get(cur[0])) {
				if (visited[next])
					continue;
				
				if(next==end) {
					return cur[1]+1;
				}
				visited[next] = true;
				q.offer(new int[] {next, cur[1]+1});
			}
		}
		
		return -1;
	}

}
