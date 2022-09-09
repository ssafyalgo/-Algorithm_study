package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_사다리조작_G3 {

	static int N, M, H, ans;
	static int[][] ladder;
	static boolean chk = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			ladder[a][b] = 1; // 오른쪽이동
			ladder[a][b + 1] = 2; // 왼쪽이동
		}

		ans = -1; // 3개추가해도 답없으면 -1 유지
		for (int i = 0; i <= 3; i++) {
			game(0, i); // 사다리 추가 개수 i개
			if (chk) {
				ans = i; // i개 추가했을때 ans
				break;
			}
		}

		System.out.println(ans);
	}

	private static void game(int cnt, int num) {
		if (cnt == num) {
			if (go())
				chk = true;
			return;
		}

		for (int i = 1; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
					ladder[i][j] = 1;
					ladder[i][j + 1] = 2;

					game(cnt + 1, num);

					ladder[i][j] = 0;
					ladder[i][j + 1] = 0;
				}
			}
		}

	}

	private static boolean go() {
		for (int i = 1; i <= N; i++) {
			int h = 1;
			int n = i;

			while (H >= h) {
				if (ladder[h][n] == 1) {
					n++;
				} else if (ladder[h][n] == 2) {
					n--;
				}
				h++;
			}
			if (n != i) {
				return false;
			}
		}
		return true;
	}

}
