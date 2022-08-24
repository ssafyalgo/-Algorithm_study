package baekjoon;

import java.util.*;
import java.io.*;

public class Main_토마토 {

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());//가로
		int M = Integer.parseInt(stz.nextToken());//세로
		int[][] map = new int[M][N];
		int[][] visit = new int[M][N];
		for(int i=0;i<M;i++) {
			Arrays.fill(visit[i], -1);
		}
		
		int tomato = 0;
		Queue<int[]> q = new LinkedList<>();
		for(int i=0;i<M;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(stz.nextToken());
				
				if(map[i][j]==1) {
					visit[i][j]=0;
					q.add(new int[]{i,j});
				}
				else if(map[i][j]==0) {
					tomato++;
				}
			}
		}
		
		int time = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int i=0;i<4;i++) {
				int nr= cur[0]+dr[i];
				int nc= cur[1]+dc[i];
				if(0<=nr&&nr<M&&0<=nc&&nc<N&&map[nr][nc]==0&&visit[nr][nc]==-1) {
					visit[nr][nc]=visit[cur[0]][cur[1]]+1;
					q.add(new int[] {nr,nc});
					tomato--;
					if(time<visit[nr][nc]) {time = visit[nr][nc];}
				}
			}
		}
		
		System.out.println(tomato!=0?-1:time);
	}

}
