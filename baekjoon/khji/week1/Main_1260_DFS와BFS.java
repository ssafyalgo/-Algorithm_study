package algo0805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_DFS와BFS {
	
	static int N, M, V;
	static int[][] map;
	static int [] dfs;
	static int [] bfs;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		V=Integer.parseInt(st.nextToken())-1;
		map = new int[N][N];
		dfs=new int[N];
		bfs=new int[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			map[s][e]=1;
			map[e][s]=1;
		}// 읽기 끝
		
		dfs(V);
		System.out.println();
		bfs();
	}
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		bfs[V]=1;
		queue.offer(V); // 이 지점에서 시작
		System.out.print((V+1)+" ");
		while(!queue.isEmpty()) {
			int start = queue.poll(); //일단 빼
			for (int e = 0; e < N; e++) {
				if(map[start][e]==1 && bfs[e]==0) { // 간선이 있고 온적이 없으면
					bfs[e]=1;
					queue.offer(e);
					System.out.print((e+1)+" ");
				}
			}
		}		
	}
	
	private static void dfs(int start) {
		dfs[start]=1;
		System.out.print((start+1)+" ");
		for (int e = 0; e < N; e++) {
			if(map[start][e]==1 && dfs[e]==0) { // 간선이 있고 온적이 없으면
				dfs(e);
			}
		}
		
	}
}
