package study; 

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 창고에 보관되는 토마토들 중 잘 익은 것도 있지만, 아직 익지 않은 토마토도 있다.
 * - 1 = 익은 토마토, 0 = 안익은 토마토, -1 = 토마토 없음
 * ! 보관 후 하루가 지나면, 익은 토마토들의 인접한 익지 않은 토마토가 -> 익는다.
 * ! 대각선 방향의 토마토에는 영향 없음
 * ! 토마토가 혼자 저절로 익는 경우도 없다. */

// Q. 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램 
//    ! 상자의 일부 칸에는 토마토가 들어있지 않을 수 있다. 
//    ! 처음부터 모두 익어있다면 0 출력, 모두 익지 못하는 상황이면 -1 출력
public class Main_7576_토마토 {
	
	static int M, N;					// 상자 크기 N x M
	static int[][] tomato;				// 토마토, 1 = 익은 토마토, 0 = 안익은 토마토, -1 = 토마토 없음
	static ArrayList<Point> initTomato;	// 초기 토마토 위치 List
	static int[][] visited;
	
	static int[] dr = {0, 1, 0, -1};	// {우, 하, 좌, 상}
	static int[] dc = {1, 0, -1, 0};
	
	static int days;					// (result) 최소 일 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/* 입력 */
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		tomato = new int[N+1][M+1];
		initTomato = new ArrayList<>();
		visited = new int[N+1][M+1];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], (int)1e9);
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				
				if (tomato[i][j]==1) initTomato.add(new Point(i, j));		// 토마토일경우, 위치 별도 저장
			}
		}
		// 확인 - 토마토
		//printTomato();
		
		/* 로직 */
		// ! 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수
		// 처음부터 모두 익어있다면 0 출력, 모두 익지 못하는 상황이면 -1 출력
		// 보관 후 하루가 지나면, 익은 토마토들의 인접한 익지 않은 토마토가 -> 익는다. (대각선 방향 X)
		
		// !!!!!!!!! testcase에 없는 예외 (토마토 농장이 0, -1만 있을 경우)
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 토마토 0, -1만 있을 때 
				// ! 1이 발견될 때
				if (tomato[i][j]==1) flag=true;
			}
		}
		if (!flag) {
			System.out.println(-1);
			System.exit(0);
		}
		
		
		// 1) 초기 상태 확인 - 처음부터 모두 익어있다면 0 출력
		if (initTomatoCheck()) {
			// 2) BFS, 4방, 최소 일수 구하기 
			Queue<Point> queue = new LinkedList<>();
			for (Point t: initTomato) {
				queue.offer(t);
				visited[t.x][t.y] = 0;
			}
			tomatoBFS(queue);
			// 3) 안 익은 토마토 있는 지 확인 -> 모두 익지 못하는 상황이면 -1 출력
			checkAfterBfsTomato();
			
			/* 출력 */
			System.out.println(days);
		}
		/* 1) 출력 */
		else {
			System.out.println(0);
		}
		
		

	}

	// [@Method] 안익은 토마토 존재 확인 - 모두 익지 못하는 상황이면 -1 출력
	private static void checkAfterBfsTomato() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomato[i][j]==0) {
					days = -1;
					return;
				}
			}
		}
	}

	// [@Method] BFS
	private static void tomatoBFS(Queue<Point> queue) {
		//Queue<Point> queue = new LinkedList<>();
		int max = 0;
		
		// BFS 
		int cr = 0;
		int cc = 0;
		while (!queue.isEmpty()) {
			Point cur = queue.poll(); 
			cr = cur.x; 
			cc = cur.y;
			
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d]; 
				int nc = cc + dc[d];
				if (!check(nr, nc)) continue;
				if (visited[nr][nc]==(int)1e9 && tomato[nr][nc]==0) {	// 방문한 적 없고 && 안익은 토마토일 경우
					visited[nr][nc] = visited[cr][cc] + 1;
					tomato[nr][nc] = 1; 
					queue.offer(new Point(nr, nc));
				}
			}
		}
		max = visited[cr][cc]; 
		days = Math.max(days, max);
		
	}

	// [@Method] 배열 범위 체크
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
	
	

	// [@Method] 초기 토마토 농장 상태 확인 - 모두 익어있는 지
	private static boolean initTomatoCheck() {
		/*final int key = 1; 									// ?? 
		for (int[] t: tomato) {
			if (!Arrays.asList(t).contains(key)) return false;
		}*/
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomato[i][j]==1) return true;
			}
		}
		
		return false;
	}

	// [@Method] 토마토 출력
	private static void printTomato() {
		System.out.println("# 토마토 농장");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(tomato[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}