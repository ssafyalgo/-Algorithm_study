package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토_G5 {

	static int N, M, ans;
	static int[][] box;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		// 1. 입력
		box = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 2. bfs
		bfs();
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		for (int i = 0; i < N; i++) { // 처음 토마토들 q에 넣기
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 1) {
					q.offer(new int[] { i, j, 0 }); // day 0으로 push
				}
			}
		}

		while (!q.isEmpty()) { // q가 빌때 까지 익은 토마토들을 queue에 넣으며 반복.
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int day = cur[2];
			ans = day; // 항상 day 최대값으로 갱신
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (chk(nr, nc) && box[nr][nc] == 0) {
					box[nr][nc] = 1;
					q.offer(new int[] { nr, nc, day + 1 }); // day값 +1해서 넣기
				}
			}
		}

		if (check()) { // 모든 토마토가 익지 않았다면 -1 출력
			System.out.println(-1);
			return;
		}

		System.out.println(ans);
	}

	private static boolean check() { // 모든 토마토가 익었는지 검사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean chk(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < M;
	}

}
