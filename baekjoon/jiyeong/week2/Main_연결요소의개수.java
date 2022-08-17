package algo0812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_연결요소의개수 {

	static int N,M;
	static boolean[][] map;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		map = new boolean[N+1][N+1];
		
		while(M-->0) {
			stz = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stz.nextToken()); 
			int b = Integer.parseInt(stz.nextToken()); 
			map[a][b]=true;
			map[b][a]=true;
		}
		
		int cnt =0;
		
		visit= new boolean[N+1];
		for(int i=1;i<=N;i++) {
			if(!visit[i]) {bfs(i); cnt++;}
		}
		
		System.out.println(cnt);
	}

	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visit[start]=true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=1;i<=N;i++) {
				if(map[cur][i]&&!visit[i]) {
					visit[i]=true;
					q.add(i);
				}
			}
		}
	}
}
