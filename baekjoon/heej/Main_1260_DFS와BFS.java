package java0809_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// DFS: recursion 사용 
// BFS: queue 사용
/* BFS가 더 많이 나옴 > 최단거리 구하라 */
// Q. 첫째 줄에는 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력한다. 
public class Main_1260_DFS와BFS {
	
	static int N, M, V;
	static int[][] map;							// 간선을 표시할 배열
	
	static int[] dfs;							// 방문 체크를 위하여 선언한 배열
	static int[] bfs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* 입력받기 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken()) - 1;					// 시작노드, 0부터 표시하기 위하여 -1 
		
		map = new int[N][N];
		dfs = new int[N];
		bfs = new int[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;		// 0부터 하기 위하여 -1
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			/* 양방향 */
			map[start][end] = 1;
			map[end][start] = 1;
		}
		// 입력받은 map 확인
		/*for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}*/
		
		/* 로직 */
		dfs(V);							// V = 시작 정점 지점
		System.out.println();
		bfs();
	}

	// [@Method] BFS 넓이 우선 탐색 - Queue 사용
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		// ![IDEA] - 시작점
		bfs[V] = 1;
		queue.offer(V);										// 이 지점에서 시작 
		
		System.out.print((V+1) + " ");
		// 공식 
		while (!(queue.isEmpty())) {
			int start = queue.poll();						// 큐의 맨 앞쪽 값을 뺌 == 현재 위치 bfs[V]값  
			
			/* start -> end, start 주변 Vertex로 간다. */
			for (int end = 0; end < N; end++) {				
				if (map[start][end]==1 && bfs[end]==0) {	// (if) 간선이 있고 온적이 없으면 
					bfs[end] = 1;						
					queue.offer(end);
					System.out.print((end+1) + " ");
				}
			}
			
		}
		
	}

	// [@Method] DFS 깊이 우선 탐색 - recursion 사용
	private static void dfs(int start) {
		dfs[start] = 1;									// dfs[] = 방문체크를 위한 배열

		System.out.print(/*"DFS " +*/ (start+1) + " ");
		for (int end = 0; end < N; end++) {				// start -> end로 가야하니까 
			if (map[start][end]==1 && dfs[end]==0) {	// (if) 간선이 있고 온적이 없으면 
				dfs(end);								//      다음껄로 감
			}
		}
		
	}

}