package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 새 집의 크기 N x N
 * 
 * 파이프
 * - 2개의 연속된 칸을 차지하는 크기
 * - 가로, 세로, 오른쪽 아래 대각선 3가지 방향으로 회전이 가능하다.
 * 
 *  파이프를 밀어서 이동시킬 수 있는데, 파이프는 항상 빈 칸만 차지해야 한다.
 *  파이프는 밀면서 회전시킬 수 있는데 
 *  파이프 회전은 45도만 가능하며, 미는 방향은 오른쪽, 아래 또는 오른쪽 아래 대각선 방향이어야 한다.
 *  
 *  파이프 회전 경우 
 *  1) 가로로 놓여진 경우 [총 2가지] : 옆으로 한칸, 오른쪽 아래 대각선
 *  2) 세로로 놓여진 경우 [총 2가지] : 아래로 한칸, 오른쪽 아래 대각선
 *  3) 대각선으로 놓여진 경우 [총 3가지] : 가로, 세로, 오른쪽 아래 대각선 */

// Q. 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 개수 구하기 
//    가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 
public class Main_17070_파이프옮기기_1 {
	
	static int N;						// 집의 크기 N x N
	static int[][] home;		
	
	static int count;
	static class Location {
		int x, y; 
		int d;							// 방향, [가로=0, 세로=1, 대각선=2]
		
		public Location(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Location [x=" + x + ", y=" + y + ", d=" + d + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/* 입력 */
		N = Integer.parseInt(br.readLine());
		
		home = new int[N][N]; 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 확인 
		//printHome();
		
		
		/* 로직 */
		// 만약, (N, N)에 파이프가 있다면 프로그램 종료 
		N -= 1;			// 파이프 (0, 0), (0, 1)로 시작하는 것으로 맞추어서 N도 하나 줄임
		if (home[N][N]==1) {
			System.out.println(0);
			System.exit(0);
		}
		// 가장 처음에 파이프는 (1, 1)와 (1, 2) -> (N, N)으로 이동시키는 방법의 개수
		// 방향에 따라 파이프가 이동할 수 있는 경우가 다름 
		// i) 가로 : 가로로 한 칸, 오른쪽 아래 대각선 
		// ii) 세로 : 아래로 한 칸, 오른쪽 아래 대각선 
		// iii) 대각선 : 가로, 세로, 오른쪽 아래 대각선
		count = 0;
		pipeBFS();
		
		/* 출력 */
		System.out.println(count);
	}
	
	// [@Method] BFS - Queue
	private static void pipeBFS() {
		Queue<Location> queue = new LinkedList<>();
		
		// 시작점 
		queue.offer(new Location(0, 1, 0));				// 방향 [가로=0, 세로=1, 대각선=2]
		
		// BFS
		while (!queue.isEmpty()) {
			Location cur = queue.poll();
			
			if (cur.x==N && cur.y==N) {
				count++;
				continue;
			}
			
			// i) 파이프 방향이 가로(0)일 경우 -> 가로, 대각선 
			if (cur.d==0) {
				
				if(checkTransverse(cur)) {						// 배열 범위 안이고 && 파이프가 없다면
					queue.offer(new Location(cur.x, cur.y+1, 0));
				}
				if(checkDiagonal(cur)) {
					queue.offer(new Location(cur.x+1, cur.y+1, 2));
				}
			}
			// ii) 파이프 방향이 세로(1)일 경우 -> 세로, 대각선
			else if (cur.d==1) {
				
				if (checkLength(cur)) {
					queue.offer(new Location(cur.x+1, cur.y, 1));
				}
				if (checkDiagonal(cur)) {
					queue.offer(new Location(cur.x+1, cur.y+1, 2));
				}
			}
			// iii) 파이프 방향이 대각선(2)일 경우 -> 가로, 세로, 대각선
			else if (cur.d==2) {
				
				if (checkTransverse(cur)) {
					queue.offer(new Location(cur.x, cur.y+1, 0));
				}
				if(checkLength(cur)) {
					queue.offer(new Location(cur.x+1, cur.y, 1));
				}
				if(checkDiagonal(cur)) {
					queue.offer(new Location(cur.x+1, cur.y+1, 2));
				}
			}
		}
	}

	
	//static int[] dr = {0, 1, 1};		// {우, 하, 우하}
	//static int[] dc = {1, 0, 1};
	// [@Method] 파이프 방향 세로 - 범위 && 파이프 있는 지 확인 
	private static boolean checkLength(Location cur) {
		return cur.x+1<=N && home[cur.x+1][cur.y]!=1;
	}

	// [@Method] 파이프 방향 대각선 - 범위 && 파이프 있는 지 확인 
	private static boolean checkDiagonal(Location cur) {
		return cur.x+1<=N && cur.y+1<=N && home[cur.x][cur.y+1]!=1 && home[cur.x+1][cur.y+1]!=1 && home[cur.x+1][cur.y]!=1;
	}

	// [@Method] 파이프 방향 가로 - 범위 && 파이프 있는 지 확인 
	private static boolean checkTransverse(Location cur) {
		return cur.y+1<=N && home[cur.x][cur.y+1]!=1;			// 위에서 N-1 했기 때문에 범위 체크 시 <=N
	}

	// [@Method] Home 출력
	private static void printHome() {
		System.out.println();
		System.out.println("@ 홈 출력");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(home[i][j] + " ");
			}
			System.out.println();
		}
	}

}
