package java08_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		Queue<int[]> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if(n==1) q.add(new int[] {i, j, 0});
			}
		}
		
		System.out.println(BFS(N, M, map, q));
	}
	
	private static boolean chkMap(int n, int m, int[][] map) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]==0) {
					return true;
				}
			}
		}
		return false;
	}

	private static int BFS(int n, int m, int[][] map, Queue<int[]> q) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		int time = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int k = 0; k < 4; k++) {
				int nr = cur[0]+dr[k];
				int nc = cur[1]+dc[k];
				time = cur[2];
				
				if(nr<0||nr>=n||nc<0||nc>=m||map[nr][nc]!=0) continue;
				
				map[nr][nc] = 1;
				q.offer(new int[] {nr, nc, time+1});
			}
		}
		
		if(chkMap(n, m, map)) return -1;
		
		return time;
	}
	
}