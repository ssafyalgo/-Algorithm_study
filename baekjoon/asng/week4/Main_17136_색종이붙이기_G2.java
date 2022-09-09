package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17136_색종이붙이기_G2 {

	static int[][] paper;
	static int ans, color;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		paper = new int[10][10];
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				if (paper[i][j] == 1) 
					color++;
			}
		}
		
		if (color == 0) {
			System.out.println(ans);
			return;
		}

		ans = Integer.MAX_VALUE;
		dfs(5, 5, 5, 5, 5, 0);

		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}

		System.out.println(ans);
	}

	private static void dfs(int c1, int c2, int c3, int c4, int c5, int cnt) {
		if (cnt>=ans) 
			return;
		
		if (anschk(c1, c2, c3, c4, c5)) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (paper[i][j] == 1) {
					for (int n = 1; n <= 5; n++) {
						if (check(i, j, n)) {
							makezero(i, j, n);
							
							switch (n) {
							case 1:
								if (c1 == 0) {
									recovery(i, j, n);
									continue;
								}
								dfs(c1 - 1, c2, c3, c4, c5, cnt + 1);
								break;
							case 2:
								if (c2 == 0) {
									recovery(i, j, n);
									continue;
								}
								dfs(c1, c2 - 1, c3, c4, c5, cnt + 1);
								break;
							case 3:
								if (c3 == 0) {
									recovery(i, j, n);
									continue;
								}
								dfs(c1, c2, c3 - 1, c4, c5, cnt + 1);
								break;
							case 4:
								if (c4 == 0) {
									recovery(i, j, n);
									continue;
								}
								dfs(c1, c2, c3, c4 - 1, c5, cnt + 1);
								break;
							case 5:
								if (c5 == 0) {
									recovery(i, j, n);
									continue;
								}
								dfs(c1, c2, c3, c4, c5 - 1, cnt + 1);
								break;
							}

							recovery(i, j, n);

						} else
							break;
					}
					return;
				}
			}
		}
	}

	private static boolean check(int r, int c, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int nr = r + i;
				int nc = c + j;
				if (nr < 0 || nc < 0 || nr >= 10 || nc >= 10) {
					return false;
				}
				if (paper[nr][nc] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	private static boolean anschk(int c1, int c2, int c3, int c4, int c5) {
		if ((5 - c1) * 1 + (5 - c2) * 4 + (5 - c3) * 9 + (5 - c4) * 16 + (5 - c5) * 25 == color) 
				return true;
		return false;
	}

	private static void makezero(int i, int j, int n) {
		for (int r = i; r < i + n; r++) {
			for (int c = j; c < j + n; c++) {
				paper[r][c] = 0;
			}
		}
	}

	private static void recovery(int i, int j, int n) {
		for (int r = i; r < i + n; r++) {
			for (int c = j; c < j + n; c++) {
				paper[r][c] = 1;
			}
		}
	}

}
