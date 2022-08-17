package algo0812;

import java.io.*;
import java.util.*;

public class Main_촌수계산 {
	static int N;
	static boolean[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(stz.nextToken());
		int end = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		while(M-->0) {
			stz = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(stz.nextToken());
			int e = Integer.parseInt(stz.nextToken());
			map[s][e] = true;
			map[e][s] = true;
		}
		
		int cnt = bfs(start, end);
		System.out.println(cnt);
	}
	
	static int bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		int[] visit = new int[101];
		Arrays.fill(visit, -1);
		q.add(start);
		visit[start]=0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur==end) {return visit[cur];}
			
			for(int i=1;i<=N;i++) {
				if(map[cur][i]&&visit[i]==-1) {
					visit[i]=visit[cur]+1;
					q.add(i);
				}
			}
		}
		return -1;
	}
}
