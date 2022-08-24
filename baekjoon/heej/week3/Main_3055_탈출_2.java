package algo.shark;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 고슴도치는 비버의 굴로 도망가 홍수를 피하려고 한다.
 * 숲 크기 R x C
 * - . = 빈 칸, * = 물, X = 돌
 * - D = 비버의 굴, S = 고슴도치 위치 
 * 
 * 매 분마다 고슴도치는 현재 있는 칸에서 4방으로 이동할 수 있다.
 * 물도 매 분마다 인접해있는 비어있는 칸 (적어도 한 변을 공유)으로 확장된다.
 * ! 물과 고슴도치 모두 돌을 통과할 수 없다.
 * ! 고슴도치는 물 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 * ! 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. */

// Q. 숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램 
//    ! 안전하게 비버의 굴로 이동할 수 없다면 "KAKTUS"를 출력한다.
public class Main_3055_탈출_2 {
	
	static int R, C;			// 숲 R x C
	static char[][] map;
		
	static ArrayList<Location> dochi;		// 고슴도치 위치 
	static Location beaver;				// 비버 굴 위치 
	static ArrayList<Location> water;		// 물 위치 
	
	static Queue<Location> queDochi;
	static Queue<Location> queWater;
	//static int[][] distance;			// 대신 level로 
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static int count;
	
	static class Location {
		int x, y, level;

		public Location(int x, int y, int level) {
			super();
			this.x = x;
			this.y = y;
			this.level = level;
		}

		@Override
		public String toString() {
			return "Location [x=" + x + ", y=" + y + ", level=" + level + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/* 입력 */
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		dochi = new ArrayList<>();
		water = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				
				/* 비버 굴, 고슴도치, 물 위치 저장 */
				if (map[i][j]=='D') beaver = new Location(i, j, 0);
				if (map[i][j]=='S') dochi.add(new Location(i, j, 0));
				if (map[i][j]=='*') water.add(new Location(i, j, 0));
			}
		}
		// 확인 
		//printMap();
		
		
		/* 로직 */
		// 물이 먼저 BFS를 돌고 
		// 그 후에 고슴도치가 BFS를 돈다. 
		// 위 과정을 1) 고슴도치가 비버네 도착했거나, 2) 고슴도치가 더이상 이동할 수 없을 때까지 반복한다.
		
		/*while(true) {
			// End 조건 - 1) 고슴도치가 비버네 도착했거나, 2) 고슴도치가 더이상 이동할 수 없을 때
			if (dochiFinishCheck() || ) {
				System.out.println(count);
			}
		}*/
		queDochi = new LinkedList<>();
		//queWater = new LinkedList<>();
		
		// 시작점 
		/*for (Location w: water) {
			queDochi.offer(w);
		}
		queDochi.offer(dochi.get(0));*/
		queDochi.offer(dochi.get(0));
		for (Location w: water) {
			queDochi.offer(w);
		}
		//printQueue();
		//distance[dochi.get(0).x][dochi.get(0).y] = 0; 
		dochiBFS();
		
		
		
	}


	// [@Method] BFS 
	private static void dochiBFS() {
		// BFS
		int nr = 0; 
		int nc = 0; 
		while (!queDochi.isEmpty()) {
			Location cur = queDochi.poll();
			//System.out.println("level: " + cur.level);
			//printMap();
			// 고슴도치 : 물X, 돌X, !! D
			//     물 : 비버굴X, 돌X  !! S
			for (int d = 0; d < 4; d++) {
				nr = cur.x + dr[d];
				nc = cur.y + dc[d];
				if (!check(nr, nc)) continue;			// 배열 범위 
				
				// i) 고슴도치 
				if (map[cur.x][cur.y]=='S') {
					/*if (map[nr][nc]=='*') {		// X
						
					}
					else if (map[nr][nc]=='X') {	// X
						
					}*/
					if (map[nr][nc]=='D') {			// O
						System.out.println(cur.level+1);
						//System.out.println(cur.level);
						//System.out.println("level: " + queDochi.poll().level);
						return;
					}
					/*else if (map[nr][nc]=='S') {	// X, S 복사할 예정 
						
					}*/
					else if (map[nr][nc]=='.') {	// O
						// count++;					// -> !! 주소값에 ++, 이런거 조심하기 
						queDochi.offer(new Location(nr, nc, cur.level+1));
						map[nr][nc]='S';
					}
				}
				// ii) 물
				else if (map[cur.x][cur.y]=='*') {
					/*if (map[nr][nc]=='*') {			// X
						
					}
					else if (map[nr][nc]=='X') {	// X
						
					}
					else if (map[nr][nc]=='D') {	// X
						
					}*/
					if (map[nr][nc]=='S') {			// O
						queDochi.offer(new Location(nr, nc, 0));
						map[nr][nc]='*';
					}
					else if (map[nr][nc]=='.') {	// O
						queDochi.offer(new Location(nr, nc, 0));
						map[nr][nc]='*';
					}
				}
				
				
			}
		}
		System.out.println("KAKTUS");
	}
	
	private static void printQueue() {
		System.out.println("@ Queue");
		while (!queDochi.isEmpty()) {
			System.out.println(queDochi.poll());
		}
	}


	// [@Method] 고슴도치가 비버네 도착했는 지 확인
	private static boolean dochiFinishCheck() {
		return true;
	}

	// [@Method] 배열 범위 체크 
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}

	// [@Method] 맵 출력
	private static void printMap() {
		System.out.println();
		System.out.println("@ 맵 출력");
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
