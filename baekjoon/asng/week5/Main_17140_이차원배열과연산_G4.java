package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17140_이차원배열과연산_G4 {

	static class Num implements Comparable<Num> {
		int n, cnt;

		public Num(int n, int cnt) {
			super();
			this.n = n;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Num o) {
			int rd = this.cnt - o.cnt;
			if (rd == 0) {
				return this.n - o.n;
			}
			return rd;
		}

	}

	static int[][] A = new int[3][3];
	static int r, c, k, ans, maxr = 3, maxc = 3;
	static int[][] temp = new int[101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (ans <= 100) {
			if (r < maxr && c < maxc) {
				if (check()) {
					break;
				}
			}

			if (maxr >= maxc) {
				R();
			} else {
				C();
			}

			A = new int[maxr][maxc];
			for (int i = 0; i < maxr; i++) {
				for (int j = 0; j < maxc; j++) {
					A[i][j] = temp[i][j];
				}
			}

			ans++;
		}

		if (ans == 101) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}

	private static void R() {
		PriorityQueue<Num> pq = new PriorityQueue<>();
		for (int[] i : temp)
			Arrays.fill(i, 0);
		int ridx = maxr;
		int cidx = maxc;
		for (int i = 0; i < ridx; i++) {
			int[] arr = new int[101];
			for (int j = 0; j < cidx; j++) {
				arr[A[i][j]]++;
			}
			for (int j = 1; j < arr.length; j++) {
				if (arr[j] > 0) {
					pq.add(new Num(j, arr[j]));
				}
			}
			int idx = 0;
			while (!pq.isEmpty()) {
				Num n = pq.poll();
				temp[i][idx] = n.n;
				temp[i][idx + 1] = n.cnt;
				idx += 2;
				maxc = Math.max(maxc, idx);
				if (idx >= 100) {
					break;
				}
			}
		}

	}

	private static void C() {
		PriorityQueue<Num> pq = new PriorityQueue<>();
		for (int[] i : temp)
			Arrays.fill(i, 0);
		int ridx = maxr;
		int cidx = maxc;
		for (int i = 0; i < cidx; i++) {
			int[] arr = new int[101];
			for (int j = 0; j < ridx; j++) {
				arr[A[j][i]]++;
			}
			for (int j = 1; j < arr.length; j++) {
				if (arr[j] > 0) {
					pq.add(new Num(j, arr[j]));
				}
			}
			int idx = 0;
			while (!pq.isEmpty()) {
				Num n = pq.poll();
				temp[idx][i] = n.n;
				temp[idx + 1][i] = n.cnt;
				idx += 2;
				maxr = Math.max(maxr, idx);
				if (idx >= 100) {
					break;
				}
			}
		}
	}

	private static boolean check() {
		if (A[r][c] == k) {
			return true;
		}
		return false;
	}
}
