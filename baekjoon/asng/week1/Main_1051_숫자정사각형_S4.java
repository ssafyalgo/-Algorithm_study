package algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1051_숫자정사각형_S4 {

	static int a;
	static int b;
	static int[][] arr;
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		a = Math.min(N, M);
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				max = Math.max(check(i,j), max);
			}
		}
		System.out.println(max*max);
	}

	private static int check(int i, int j) {
		int len = 1;
		for (int d = 1; d < a; d++) {
			if ((i+d)>=0 && (i+d)<N &&(j+d)>=0 && (j+d)<M) {
				if (arr[i][j] == arr[i][j + d] && arr[i][j] == arr[i + d][j] && arr[i][j] == arr[i + d][j + d]) {
					len = d+1;
				}
			}
		}
		return len;
	}
}

