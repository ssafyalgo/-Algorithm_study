package java0915;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 색종이 크기는 1x1 ... 5x5까지 총 다섯 종류의 색종이를 5개씩 가지고 있다.
 * 
 * 색종이를 10x10인 종이 위에 1이 적힌 칸은 모두 색종이로 덮어져야 한다. 
 * ! 색종이를 붙일 때 종이의 경계 밖으로 나가서도, 겹쳐서도 안된다. 
 * ! 칸의 경계와 일치하게 붙어야 한다. 
 * ! 0이 적힌 칸에는 색종이가 있으면 안된다. */

// Q. 종이가 주어졌을 때, 1이 적힌 모든 칸에 붙이는데 필요한 색종이의 최소 개수를 구하라 
//    - 1을 모두 덮는 것이 불가능한 경우 -1을 출력한다. 
public class Main_17136_색종이붙이기 {
	
	static int[][] map = new int[10][10]; 			// 10x10 종이 
	static int[] colorPaper = {0, 5, 5, 5, 5, 5};	// 1x1 ... 5x5까지 총 다섯 종류의 색종이를 5개씩
	
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		/* 입력 */
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		// 확인 
		//printMap();
		
		
		/* 로직 */
		// DFS + 백트래킹 사용 
		// DFS를 사용하여 색종이를 붙일 수 있는지 체크 -> 붙일 수 있는 색종이 최대크기 확인 
		// ! 색종이를 붙일 수 있는 모든 경우를 탐색하기 위해 색종이 크기가 큰 것부터 순서대로 돌기 
		// 남은 색종이 개수를 관리할 배열 colorPaper 생성 
		pasteDFS(0, 0, 0);

		
		/* 출력 */
		if (result==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
	}

	// [@Method] DFS - 색종이 붙이기 
	private static void pasteDFS(int r, int c, int cnt) {
		// if) 마지막 위치(9, 10)까지 갔을 경우 return --> (10, 10)으로 할 시 x가 10일때도 탐색을 돌기 때문에 outofindex 발생
		if (r==9 && c==10) {
			result = Math.min(result, cnt);
			return;
		}
		
		// if) 현재 cnt가 result보다 크다면 탐색 필요없음
		if (cnt >= result) return; 
		
		// if) 오른쪽 범위를 벗어나면 아랫줄 탐색 
		if (c > 9) {
			pasteDFS(r+1, 0, cnt);
			return;
		}
		
		// if) 색종이를 붙일 수 있다면 
		if (map[r][c]==1) {
			for (int i = 5; i >= 1; i--) {					// 최대크기 5의 색종이부터 확인 
				if (colorPaper[i]>0 && check(r, c, i)) {	// i 크기의 색종이가 남아있고 && i 크기 배열 범위 초과 + 0인 칸이 있는지 확인
					colorPaper[i]--;
					chageMap(r, c, i, 0);					// i 크기의 색종이 붙이기 (0로 표시)
					pasteDFS(r, c+1, cnt+1); 				// DFS 재귀호출 
					
					chageMap(r, c, i, 1);					// i 크기의 색종이 떼기 (1으로 표시)
					colorPaper[i]++;
				}
			}
		}
		// else) 붙일 수 없다면, 다음 열 탐색 
		else {
			pasteDFS(r, c+1, cnt);
		}
	}

	// [@Method] 색종이 붙이기 or 떼기에 따른 Map 표시 
	private static void chageMap(int r, int c, int range, int state) {
		for (int i = 0; i < range; i++) {
			for (int j = 0; j < range; j++) {
				map[r+i][c+j] = state;
			}
		}
	}

	// [@Method] range - 배열 범위 체크 
	private static boolean check(int r, int c, int range) {
		for (int i = 0; i < range; i++) {
			for (int j = 0; j < range; j++) {
				// i) 배열 범위 체크 
				if (r+i > 9 || c+j > 9) return false; 			// (9, 9) 좌표가 마지막 좌표 
				// ii) 색종이를 붙일 수 있는지 체크 (1 인지) 
				if (map[r+i][c+j] != 1) return false;
			}
		}
		return true;
	}

	// [@Method] Map 출력
	private static void printMap() {
		System.out.println();
		System.out.println("@ Map 출력");
		for (int i = 0; i < 10; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}