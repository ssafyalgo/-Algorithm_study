package java0809_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* BFS */

/* 1번부터 N번까지의 도서와 M개의 단방향 도로 */
// Q. 도시 X에서 출발하여 도달할 수 있는 도시 중 최단 거리가 정확히 K인 모든 도시들의 번호를 출력하시오
//    - 최단 거리가 K인 모든 도시의 번호를 한 줄에 하나씩 오름차순으로 출력
//    - 하나도 존재하지 않는다면 -1을 출력
public class Main_18352_특정거리의도시찾기_2 {
	
	static int N, M, K, X;
	static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	static int[] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* 입력받기 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());							// 노드 개수
		M = Integer.parseInt(st.nextToken());							// 도로의 개수
		K = Integer.parseInt(st.nextToken());							// 최소 거리
		X = Integer.parseInt(st.nextToken());							// 시작점, 배열 index와 맞추기 위하여 -1
		
		// 거리계산을 위해 생성
		distance = new int[N+1];
		Arrays.fill(distance, (int)1e9);								// (int)1e9 == 무한, 10억
		
		// map 입력받기
		for (int i = 0; i < N+1; i++) {
			map.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map.get(x).add(y);
		}
		// 입력받은 map 확인
		// test_case 1번으로 보면 __1번 노드 -> 2, 3 간선 연결, 2번 노드 -> 3, 4번 간선 연결
		/*for (ArrayList<Integer> m: map) {
			System.out.println(m);
		}*/
		
		
		/* 로직 */
		bfs();
		
		
		/* 출력 */
		boolean flag = false;
		for (int i = 0; i < N+1; i++) {
			if (distance[i]==K) {
				flag = true;
				System.out.println(i);
			}
		}
		if (flag==false) System.out.println(-1);
		
	}

	
	// [@Method] BFS 넓이 우선 탐색 
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작점
		queue.add(X);
		distance[X] = 0;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			
			for (int i : map.get(current)) {
				if (distance[i] == (int)1e9) {
					distance[i] = distance[current] + 1;		// 전 거리 + 1 = 현재 거리
					queue.offer(i);
				}
			}
		}
		//System.out.println(Arrays.toString(distance));
	}

}