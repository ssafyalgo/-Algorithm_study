package java08_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {
	static int[] dr = { 0, 1, 1}; //우, 하, 대각
	static int[] dc = { 1, 0, 1};
	static int[][] map;
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0, 1, 0, N-1);
		System.out.println(cnt);
	}
	private static void DFS(int r, int c, int d, int N) {
		if(r==N&&c==N) {
			cnt++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if(nr<0||nr>N||nc<0||nc>N||map[nr][nc]==1) continue;
			if((d==0 && i==1) || (d==1 && i==0)) continue;
			if(i==2 && (map[nr-1][nc]==1 || map[nr][nc-1]==1)) continue;
			
			DFS(nr, nc, i, N);
		}
	}

}
