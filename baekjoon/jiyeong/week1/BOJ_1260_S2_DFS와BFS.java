import java.util.*;
import java.io.*;

public class BOJ_1260_S2_DFS와BFS {

	static int N,M,S;
	static LinkedList<Integer>[] grp;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		S = Integer.parseInt(stz.nextToken());
		grp = new LinkedList[N+1];
		for(int i=1;i<=N;i++) {
			grp[i]=new LinkedList<>();
		}
		while(M-->0) {
			stz = new StringTokenizer(br.readLine());
			int N1 = Integer.parseInt(stz.nextToken());
			int N2 = Integer.parseInt(stz.nextToken());
			grp[N1].add(N2);
			grp[N2].add(N1);
		}
		for(int i=1;i<=N;i++) {
			Collections.sort(grp[i]);
		}
		
		sb = new StringBuilder();
		
		//DFS
		boolean[] visit = new boolean[N+1];
		DFS(S,visit);
		sb.append('\n');
		//BFS
		BFS(S);
		
		System.out.println(sb.toString());
		
	}

	static void DFS(int start, boolean[] visit) {
		visit[start]=true;
		sb.append(start).append(' ');
		
		for(int g: grp[start]) {
			if(!visit[g]) {
				DFS(g,visit);
			}
		}
	}
	
	static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[N+1];
		q.add(start);
		visit[start]=true;
		sb.append(start).append(' ');
		
		while(!q.isEmpty()) {
			int cur=q.poll();
			for(int g: grp[cur]) {
				if(!visit[g]) {
					visit[g]=true;
					q.add(g);
					sb.append(g).append(' ');
				}
			}
		}
	}
}
