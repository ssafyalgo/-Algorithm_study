package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17979_파이프옮기기_G5 {

	static int N, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0, 1);
		System.out.println(ans);
	}

	private static void dfs(int sx, int sy, int dx, int dy) {
		if (!check(sx, sy, dx, dy)) { // map 범위 밖이면 return
			return;
		}

		if (cal(sx, sy, dx, dy) == 2) { // 대각방향일땐
			if (map[dx][dy] == 1 || map[dx - 1][dy] == 1 || map[dx][dy - 1] == 1) { // 파이프 끝 기준으로 3방향 벽이면 return
				return;
			}
		} else { // 가로세로 방향일땐
			if (map[dx][dy] == 1) { // 파이프 끝이 벽이면 return
				return;
			}

		}

		if (dx == N - 1 && dy == N - 1) { // 파이프 끝이 (N,N)도착이면 ans++ 하고 return
			ans++;
			return;
		}

		if (cal(sx, sy, dx, dy) == 2) { // 대각 모양일떄
			dfs(sx + 1, sy + 1, dx, dy + 1);
			dfs(sx + 1, sy + 1, dx + 1, dy + 1);
			dfs(sx + 1, sy + 1, dx + 1, dy);
		} else {
			if (Math.abs(sx - dx) == 1) { // 세로모양일때
				dfs(sx + 1, sy, dx + 1, dy);
				dfs(sx + 1, sy, dx + 1, dy + 1);
			} else { // 가로모양일때
				dfs(sx, sy + 1, dx, dy + 1);
				dfs(sx, sy + 1, dx + 1, dy + 1);
			}
		}

	}

	private static boolean check(int sx, int sy, int dx, int dy) {
		return (sx >= 0 && sx < N && sy >= 0 && sy < N) && (dx >= 0 && dy >= 0 && dx < N && dy < N);
	}

	private static int cal(int sx, int sy, int dx, int dy) {
		return Math.abs(sx - dx) + Math.abs(sy - dy);
	}

}
